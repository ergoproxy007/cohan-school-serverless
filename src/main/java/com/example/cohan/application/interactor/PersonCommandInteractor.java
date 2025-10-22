package com.example.cohan.application.interactor;

import com.example.cohan.application.mapper.output.ResponseMapper;
import com.example.cohan.domain.exceptions.ApiException;
import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;
import com.example.cohan.domain.mapper.DomainMapper;
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
    public PersonResponse create(PersonRequest request) {
        return switch (request.getType()) {
            case STUDENT -> save(DomainMapper.toStudent(request));
            case TEACHER -> save(DomainMapper.toTeacher(request));
        };
    }

    private PersonResponse save(Person person) {
        try {
            port.save(person);
            return ResponseMapper.toPersonResponse(
                    "success",
                    "Person created successfully"
            );
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

    private String getSavingError(String dni) {
        return "[PersonCommandInteractor] Error occurred saving a person [dni:%s]".formatted(dni);
    }
}
