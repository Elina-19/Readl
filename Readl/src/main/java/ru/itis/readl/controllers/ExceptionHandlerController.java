package ru.itis.readl.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itis.readl.exceptions.ReadlForbiddenException;
import ru.itis.readl.exceptions.ReadlNotFoundException;
import ru.itis.readl.validation.http.ValidationErrorDto;
import ru.itis.readl.validation.http.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponse> handleValidExceptions(MethodArgumentNotValidException exception){

        List<ValidationErrorDto> errors = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else {
                String objectName = error.getObjectName();
                errorDto.setObjectName(objectName);
            }

            errors.add(errorDto);
        });

        return new ResponseEntity<>(ValidationExceptionResponse.builder()
                .errors(errors)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ReadlNotFoundException.class)
    public String handleNotFoundException(){
        return "code_pages/notFound";
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ReadlForbiddenException.class)
    public String handleForbiddenExceptions(){
        return "code_pages/forbidden";
    }
}
