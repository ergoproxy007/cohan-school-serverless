package com.example.cohan.application.interactor;

import com.example.cohan.application.mapper.output.ResponseMapper;
import com.example.cohan.domain.exceptions.ApiException;
import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.exceptions.TechnicalException;
import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.input.PersonUpdateRequest;
import com.example.cohan.domain.http.output.PersonSucessResponse;
import com.example.cohan.domain.http.output.StudentResponse;
import com.example.cohan.domain.http.output.TeacherResponse;
import com.example.cohan.domain.mapper.DomainMapper;
import com.example.cohan.domain.school.enums.CodeErrorEnum;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.port.input.PersonPort;
import com.example.cohan.domain.usecase.PersonCommandUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandInteractor implements PersonCommandUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonCommandInteractor.class);

    private final PersonPort port;

    @Autowired
    public PersonCommandInteractor(PersonPort port) {
        this.port = port;
    }

    @Override
    public StudentResponse findStudent(String dni) {
        try {
            return port.getStudent(dni)
                    .map(ResponseMapper::toResponse)
                    .orElseThrow(() -> new BusinessException.NotFound(
                            CodeErrorEnum.PERSON_FIND_BUSINESS_ERROR,
                            "[dni:%s] not exists".formatted(dni)));
        } catch (BusinessException be) {
            throw handleBusinessException(be);
        } catch (Exception e) {
            String messageError = "Error occurred trying to find a student [dni:%s]".formatted(dni);
            throw handleInternalServerError(CodeErrorEnum.STUDENT_FIND_BY_DNI_ERROR, messageError, e);
        }
    }

    @Override
    public TeacherResponse findTeacher(String dni) {
        try {
            return port.getTeacher(dni)
                    .map(ResponseMapper::toResponse)
                    .orElseThrow(() -> new BusinessException.NotFound(
                            CodeErrorEnum.PERSON_FIND_BUSINESS_ERROR,
                            "[dni:%s] not exists".formatted(dni)));
        } catch (BusinessException be) {
            throw handleBusinessException(be);
        } catch (Exception e) {
            String messageError = "Error occurred trying to find a teacher [dni:%s]".formatted(dni);
            throw handleInternalServerError(CodeErrorEnum.TEACHER_FIND_BY_DNI_ERROR, messageError, e);
        }
    }

    @Override
    public PersonSucessResponse create(PersonRequest request) {
        var person = getPerson(request);
        var type = request.getType();
        var id = savePerson(person, type);
        return createSuccessResponse(id, type);
    }

    private Long savePerson(Person person, PersonType type) {
        try {
            validatePersonIfExists(person.getDni(), type);
            return save(person);
        } catch (BusinessException be) {
            throw handleBusinessException(be);
        } catch (TechnicalException te) {
            LOGGER.error(te.getMessage(), te);
            throw te;
        } catch (Exception e) {
            String messageError = getSavingError(person.getDni());
            throw handleInternalServerError(CodeErrorEnum.PERSON_SAVE_INTERNAL_ERROR, messageError, e);
        }
    }

    private Long save(Person person) {
        var entitySaved = port.save(person);
        if (!entitySaved.isPresent()) {
            String messageError = "Failed to save person: No ID returned";
            LOGGER.warn("[PersonCommandInteractor] " + messageError);
            throw new TechnicalException(
                    CodeErrorEnum.PERSON_SAVE_TECHNICAL_ERROR.getCode(),
                    CodeErrorEnum.PERSON_SAVE_TECHNICAL_ERROR.getOrigin(),
                    messageError);
        }
        return entitySaved.get();
    }

    @Override
    public PersonSucessResponse update(PersonUpdateRequest request) {
        try {
            switch (request.getType()) {
                case STUDENT -> port.updateTeacher(
                        request.getId(),
                        request.getDni(),
                        request.getPhoneNumber(),
                        request.getEmail(),
                        request.getAverageMark()
                );
                case TEACHER -> port.updateTeacher(
                        request.getId(),
                        request.getDni(),
                        request.getPhoneNumber(),
                        request.getEmail(),
                        request.getSalary()
                );
            };
            return ResponseMapper.toPersonResponse(
                    request.getId(),
                    "success",
                    "%s updated successfully".formatted(request.getType())
            );
        } catch (BusinessException be) {
            throw handleBusinessException(be);
        } catch (TechnicalException te) {
            LOGGER.error(te.getMessage(), te);
            throw te;
        } catch (Exception e) {
            String messageError = "Error occurred trying to update a person [dni:%s]".formatted(request.getDni());
            throw handleInternalServerError(CodeErrorEnum.PERSON_UPDATE_INTERNAL_ERROR, messageError, e);
        }
    }

    private Person getPerson(PersonRequest request) {
        return switch (request.getType()) {
            case STUDENT -> DomainMapper.toStudent(request);
            case TEACHER -> DomainMapper.toTeacher(request);
        };
    }

    private PersonSucessResponse createSuccessResponse(Long id, PersonType type) {
        return ResponseMapper.toPersonResponse(id, "success", "%s created successfully".formatted(type));
    }

    private void validatePersonIfExists(String dni, PersonType type) {
        if (port.existsByDni(dni, type)) {
            throw new BusinessException.Conflict(
                    CodeErrorEnum.PERSON_SAVE_IF_EXISTS,
                    "DNI %s already exists as a %s"
                            .formatted(dni, type.name().toLowerCase())
            );
        }
    }

    private String getSavingError(String dni) {
        return "Error occurred saving a person [dni:%s]".formatted(dni);
    }

    private BusinessException handleBusinessException(BusinessException be) {
        LOGGER.warn("[PersonCommandInteractor] " + be.getMessage(), be);
        return be;
    }

    private ApiException handleInternalServerError(
            CodeErrorEnum codeErrorEnum,
            String messageError,
            Exception exception
    ) {
        LOGGER.error("[PersonCommandInteractor]" + messageError + " [error:%s]".formatted(exception.getMessage()), exception);
        throw new ApiException.InternalServerError(codeErrorEnum.getCode(), codeErrorEnum.getOrigin(), messageError);
    }

}
