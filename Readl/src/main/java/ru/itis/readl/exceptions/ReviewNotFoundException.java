package ru.itis.readl.exceptions;

public class ReviewNotFoundException extends ReadlNotFoundException{
    public ReviewNotFoundException() {
        super("Review not found");
    }
}
