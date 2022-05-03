package ru.itis.readl.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReadlNotFoundException extends ReadlServiceException {
    public ReadlNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
