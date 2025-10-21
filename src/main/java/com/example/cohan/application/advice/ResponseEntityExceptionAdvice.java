package com.example.cohan.application.advice;

import com.example.cohan.application.advice.dto.DtoResponseEntityException;
import com.example.cohan.domain.exceptions.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

public abstract class ResponseEntityExceptionAdvice {

    public ResponseEntity<DtoResponseEntityException> processStatus(
            DomainException domainException,
            HttpStatus status
    ) {
        return ResponseEntity.status(status).body(new DtoResponseEntityException(domainException));
    }

    public ResponseEntity<?> processBadRequest(List<String> errors) {
        return ResponseEntity.badRequest().body(new DtoResponseEntityException(errors));
    }
}
