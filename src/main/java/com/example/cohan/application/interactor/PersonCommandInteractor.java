package com.example.cohan.application.interactor;

import com.example.cohan.application.mapper.output.ResponseMapper;
import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;
import com.example.cohan.domain.school.port.input.PersonCommandUseCase;
import org.springframework.stereotype.Service;

@Service
public class PersonCommandInteractor implements PersonCommandUseCase {

    @Override
    public PersonResponse create(PersonRequest request) {

        return ResponseMapper.toPersonResponse(
                "success",
                "Person created successfully"
        );
    }
}
