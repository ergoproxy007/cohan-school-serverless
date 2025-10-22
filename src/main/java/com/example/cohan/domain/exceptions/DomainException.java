package com.example.cohan.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {
    private final String codeError;
    private final String originError;
    private final String message;

    public DomainException(
            String codeError,
            String originError,
            String message
    ) {
        super(message);
        this.codeError = codeError;
        this.originError = originError;
        this.message = message;
    }
}
