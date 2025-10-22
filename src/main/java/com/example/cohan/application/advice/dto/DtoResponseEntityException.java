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
        this(domainException.getCodeError(), domainException.getOriginError(), domainException.getMessage());
    }

    public DtoResponseEntityException(
            String codeError,
            String originError,
            String message
    ) {
        this.codeError = codeError;
        this.originError = originError;
        this.errors = Collections.singletonList(message);
    }

    public DtoResponseEntityException(List<String> errors) {
        this.errors = errors;
    }
}
