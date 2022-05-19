package ru.itis.readl.exceptions;

public class VkAuthException extends ReadlForbiddenException{
    public VkAuthException() {
        super("Account don't have email");
    }
}
