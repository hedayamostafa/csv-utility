package com.example.rest.exception;

import com.example.rest.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setErrorMessage("An error occurred. Please try again later.");
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
