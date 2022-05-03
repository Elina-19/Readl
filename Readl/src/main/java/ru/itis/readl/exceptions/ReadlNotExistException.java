package ru.itis.readl.exceptions;

import org.springframework.http.HttpStatus;

public class ReadlNotExistException extends ReadlServiceException {
    public ReadlNotExistException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
