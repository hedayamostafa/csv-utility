package com.example.rest.exception;

import com.example.rest.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {InvalidInputException.class})
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex) {
        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {ItemNotFoundExceptionHandler.class})
    public ResponseEntity<ErrorResponse> handleItemNotFoundException(ItemNotFoundExceptionHandler ex) {
        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
