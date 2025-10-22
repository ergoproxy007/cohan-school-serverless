package com.example.cohan.domain.exceptions;

import com.example.cohan.domain.school.enums.CodeErrorEnum;
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
        public BadRequest(CodeErrorEnum codeErrorEnum, String message) {
            super(codeErrorEnum.getCode(), codeErrorEnum.getOrigin(), message, HttpStatus.BAD_REQUEST);
        }
    }

    public static class Conflict extends BusinessException {
        public Conflict(CodeErrorEnum codeErrorEnum, String message) {
            super(codeErrorEnum.getCode(), codeErrorEnum.getOrigin(), message, HttpStatus.CONFLICT);
        }
    }

    public static class NotFound extends BusinessException {
        public NotFound(CodeErrorEnum codeErrorEnum, String message) {
            super(codeErrorEnum.getCode(), codeErrorEnum.getOrigin(), message, HttpStatus.NOT_FOUND);
        }
    }
}
