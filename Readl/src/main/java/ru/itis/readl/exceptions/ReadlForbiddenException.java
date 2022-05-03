package ru.itis.readl.exceptions;

import org.springframework.http.HttpStatus;

public class ReadlForbiddenException extends ReadlServiceException {
    public ReadlForbiddenException() {
        super(HttpStatus.FORBIDDEN, "You haven't access to this page");
    }
}
