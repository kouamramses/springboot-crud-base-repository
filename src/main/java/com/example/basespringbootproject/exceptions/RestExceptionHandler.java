package com.example.basespringbootproject.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice

public class RestExceptionHandler {

    private String messageToSend="";

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex) {
        ErrorHandlerModel errorHandler = new ErrorHandlerModel(NOT_FOUND);
        errorHandler.setMessage(ex.getMessage());
        errorHandler.setDebugMessage(ex.getLocalizedMessage());
        return getResponseError(errorHandler);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object>  handleResponseException(Exception ex) {
        ErrorHandlerModel errorHandler = new ErrorHandlerModel(NOT_ACCEPTABLE);

        errorHandler.setDebugMessage(ex.getLocalizedMessage());
        if (ex instanceof NumberFormatException) {
            errorHandler.setStatus(NOT_ACCEPTABLE);
            errorHandler.setMessage(ex.getMessage());
        } else if (ex instanceof InvalidMediaTypeException) {
            errorHandler.setStatus(UNSUPPORTED_MEDIA_TYPE);
            errorHandler.setMessage(ex.getMessage());
        } else if (ex instanceof SecurityException) {
            errorHandler.setStatus(FORBIDDEN);
            errorHandler.setMessage(ex.getMessage());
        } else if (ex instanceof ConstraintViolationException) {
            errorHandler.setStatus(CONFLICT);
            ((ConstraintViolationException) ex).getConstraintViolations().forEach(constraintViolation -> {
                messageToSend=messageToSend+constraintViolation.getMessageTemplate()+";";
            }) ;
            errorHandler.setMessage(messageToSend);
        } else {
            errorHandler.setStatus(BAD_REQUEST);
            errorHandler.setMessage(ex.getMessage());
        }
        return getResponseError(errorHandler);
    }

    private ResponseEntity<Object> getResponseError(ErrorHandlerModel errorHandler) {
        return new ResponseEntity<>(errorHandler, errorHandler.getStatus());
    }
}
