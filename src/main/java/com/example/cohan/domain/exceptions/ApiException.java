package com.example.cohan.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends TechnicalException {

    private final HttpStatus status;

    public ApiException(
            String codeError,
            String originError,
            String message,
            HttpStatus status
    ) {
        super(codeError, originError, String.format(message + ",[status:%s]", status.value()));
        this.status = status;
    }

    public static class NotFound extends ApiException {
        public NotFound(
                String codeError,
                String originError,
                String message
        ) {
            super(codeError, originError, message, HttpStatus.NOT_FOUND);
        }
    }

    public static class InternalServerError extends ApiException {
        public InternalServerError(
                String codeError,
                String originError,
                String message
        ) {
            super(codeError, originError, message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
