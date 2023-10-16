package com.example.rest.util;

import com.example.rest.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
@Slf4j
public class ErrorUtils {

    public void handleBindingResultErrors(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errorList = result.getFieldErrors();

            List<String> errorMessages = errorList.stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            String errorMessage = String.join(", ", errorMessages);
            log.error("Invalid input: {}", errorMessage);
            throw new InvalidInputException(errorMessage);
        }
    }
}
