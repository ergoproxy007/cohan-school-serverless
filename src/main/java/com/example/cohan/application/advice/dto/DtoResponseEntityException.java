package com.example.cohan.application.advice.dto;

import com.example.cohan.domain.exceptions.DomainException;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class DtoResponseEntityException {

    private String codeError;
    private String originError;
    private final List<String> errors;

    public DtoResponseEntityException(DomainException domainException) {
        this.codeError = domainException.getCodeError();
        this.originError = domainException.getCodeError();
        this.errors = Collections.singletonList(domainException.getMessage());
    }

    public DtoResponseEntityException(List<String> errors) {
        this.errors = errors;
    }
}
