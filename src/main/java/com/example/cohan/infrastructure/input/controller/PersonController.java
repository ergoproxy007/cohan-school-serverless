package com.example.cohan.infrastructure.input.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.cohan.application.mapper.output.ResponseMapper;
import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.PersonResponse;
import com.example.cohan.domain.school.port.input.PersonCommandUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonCommandUseCase personCommandUseCase;

    @Autowired
    public PersonController(
            PersonCommandUseCase personCommandUseCase
    ) {
        this.personCommandUseCase = personCommandUseCase;
    }

    private static final String PERSON_ROUTE = "/person";

    @PostMapping(
            value = PERSON_ROUTE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonResponse> create(
            @Valid @RequestBody PersonRequest request
    ) {
        return ok(personCommandUseCase.create(request));
    }
}
