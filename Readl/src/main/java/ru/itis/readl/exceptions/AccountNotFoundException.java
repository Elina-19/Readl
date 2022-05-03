package ru.itis.readl.exceptions;

public class AccountNotFoundException extends ReadlNotFoundException {
    public AccountNotFoundException() {
        super("Account not found");
    }
}
