package com.example.cohan.infrastructure.input.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.cohan.domain.http.input.PersonRequest;
import com.example.cohan.domain.http.output.StudentResponse;
import com.example.cohan.domain.http.output.TeacherResponse;
import com.example.cohan.domain.http.output.PersonSucessResponse;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.usecase.PersonCommandUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private final PersonCommandUseCase personCommandUseCase;

    private static final String CREATE_ROUTE = "/{type}";
    private static final String TEACHER_ROUTE = "/teacher/{dni}";
    private static final String STUDENT_ROUTE = "/student/{dni}";


    @Autowired
    public PersonController(
            PersonCommandUseCase personCommandUseCase
    ) {
        this.personCommandUseCase = personCommandUseCase;
    }

    @GetMapping(value = TEACHER_ROUTE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherResponse> findTeacher(@PathVariable(name = "dni") String dni) {
        return ok(personCommandUseCase.findTeacher(dni));
    }

    @GetMapping(value = STUDENT_ROUTE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentResponse> findStudent(@PathVariable(name = "dni") String dni) {
        return ok(personCommandUseCase.findStudent(dni));
    }

    @PostMapping(
            value = CREATE_ROUTE,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonSucessResponse> create(
            @Valid @RequestBody PersonRequest request,
            @PathVariable String type
    ) {
        if (request.getType() == null) {
            request.setType(PersonType.valueOf(type.toUpperCase()));
        }
        return ok(personCommandUseCase.create(request));
    }
}
