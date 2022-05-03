package ru.itis.readl.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReadlServiceException extends RuntimeException{
    private final HttpStatus httpStatus;

    public ReadlServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
