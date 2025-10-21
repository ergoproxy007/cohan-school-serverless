package com.example.cohan.application.interactor;

import com.example.cohan.application.mapper.output.ResponseMapper;
import com.example.cohan.domain.exceptions.ApiException;
import com.example.cohan.domain.exceptions.TechnicalException;
import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;
import com.example.cohan.domain.school.port.input.PersonCommandUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandInteractor implements PersonCommandUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonCommandInteractor.class);

    @Override
    public PersonResponse create(PersonRequest request) {
        return save(request);
    }

    private PersonResponse save(PersonRequest request) {
        try {
            return ResponseMapper.toPersonResponse(
                    "success",
                    "Person created successfully"
            );
        } catch (TechnicalException te) {
            LOGGER.error(te.getMessage(), te);
            throw te;
        } catch (Exception e) {
            String messageError = String.format(
                    "[PersonCommandInteractor] Error occurred saving a person [dni:%s]",
                    request.getDni()
            );
            LOGGER.error(messageError, e);
            throw new ApiException.InternalServerError(
                    "SAVE_PERSON_001",
                    "SAVE_PERSON_SERVICE",
                    messageError
            );
        }
    }
}
