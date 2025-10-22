package com.example.cohan.application.advice;

import com.example.cohan.domain.exceptions.ApiException;
import com.example.cohan.domain.exceptions.BusinessException;
import com.example.cohan.domain.exceptions.TechnicalException;
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

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleBusinessException(BusinessException be) {
        return processStatus(be, be.getStatus());
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<?> handleTechnicalException(TechnicalException te) {
        return processTechnicalException(te.getCodeError(), te.getOriginError());
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
