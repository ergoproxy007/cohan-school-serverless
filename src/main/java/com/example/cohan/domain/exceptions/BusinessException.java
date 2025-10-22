package com.example.cohan.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends DomainException {

    private final HttpStatus status;

    public BusinessException(
            String codeError,
            String originError,
            String message,
            HttpStatus status
    ) {
        super(codeError, originError, message);
        this.status = status;
    }

    public static class BadRequest extends BusinessException {
        public BadRequest(
                String codeError,
                String originError,
                String message
        ) {
            super(codeError, originError, message, HttpStatus.BAD_REQUEST);
        }
    }

    public static class Conflict extends BusinessException {
        public Conflict(
                String codeError,
                String originError,
                String message
        ) {
            super(codeError, originError, message, HttpStatus.CONFLICT);
        }
    }
}
