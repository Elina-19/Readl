package ru.itis.readl.exceptions;

public class AccountNotExistException extends ReadlNotExistException {
    public AccountNotExistException() {
        super("Account with that id does not exist");
    }
}
