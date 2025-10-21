package com.example.cohan.application.advice;

import com.example.cohan.domain.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;import java.util.stream.Collectors;

@ControllerAdvice
public class ResponseEntityExceptionAdviceImpl extends ResponseEntityExceptionAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleAppException(ApiException ex) {
        return processStatus(ex, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return processBadRequest(errors);
    }
}
