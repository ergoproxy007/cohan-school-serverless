package com.example.cohan.application.interactor;

import com.example.cohan.application.mapper.output.ResponseMapper;
import com.example.cohan.domain.exceptions.ApiException;
import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.exceptions.TechnicalException;
import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;
import com.example.cohan.domain.mapper.DomainMapper;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.school.model.Person;
import com.example.cohan.domain.school.port.input.PersonPort;
import com.example.cohan.domain.usecase.PersonCommandUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonCommandInteractor implements PersonCommandUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonCommandInteractor.class);

    private final PersonPort port;

    @Autowired
    public PersonCommandInteractor(PersonPort port) {
        this.port = port;
    }

    @Override
    public PersonResponse create(PersonRequest request) {
        return switch (request.getType()) {
            case STUDENT -> savePerson(DomainMapper.toStudent(request), request.getType());
            case TEACHER -> savePerson(DomainMapper.toTeacher(request), request.getType());
        };
    }

    private PersonResponse savePerson(Person person, PersonType type) {
        validatePersonNotExists(person.getDni(), type);
        var entitySaved = save(person);
        if (entitySaved.isPresent()) {
            return createSuccessResponse(entitySaved.get(), type);
        } else {
            String messageError = "[PersonCommandInteractor] Failed to save person: No ID returned";
            LOGGER.warn(messageError);
            throw new TechnicalException("PERSON_SAVE_003", "PERSON_SAVE_SERVICE", messageError);
        }
    }
    
    private void validatePersonNotExists(String dni, PersonType type) {
        if (port.existsByDni(dni, type)) {
            throw new BusinessException.Conflict(
                    "PERSON_SAVE_002",
                    "PERSON_SAVE_PERSON_INTERACTOR",
                    "[PersonCommandInteractor] DNI %s already exists as a %s"
                            .formatted(dni, type.name().toLowerCase())
            );
        }
    }

    private Optional<Long> save(Person person) {
        try {
            return port.save(person);
        } catch (BusinessException be) {
            LOGGER.error(be.getMessage(), be);
            throw be;
        } catch (Exception e) {
            String messageError = getSavingError(person.getDni());
            LOGGER.error(messageError + " [error:%s]".formatted(e.getMessage()), e);
            throw new ApiException.InternalServerError(
                    "PERSON_API_001",
                    "PERSON_SAVE_SERVICE",
                    messageError
            );
        }
    }
    
    private PersonResponse createSuccessResponse(Long id, PersonType type) {
        return ResponseMapper.toPersonResponse(id, "success", "%s created successfully".formatted(type));
    }

    private String getSavingError(String dni) {
        return "[PersonCommandInteractor] Error occurred saving a person [dni:%s]".formatted(dni);
    }
}
