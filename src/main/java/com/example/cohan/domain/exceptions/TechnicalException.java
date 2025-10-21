package com.example.cohan.domain.exceptions;

public class TechnicalException extends DomainException {

    public TechnicalException(
            String codeError,
            String originError,
            String message
    ) {
        super(codeError, originError, message);
    }
}
