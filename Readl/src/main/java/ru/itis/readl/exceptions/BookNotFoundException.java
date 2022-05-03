package ru.itis.readl.exceptions;

public class BookNotFoundException extends ReadlNotFoundException{
    public BookNotFoundException() {
        super("Book not found");
    }
}
