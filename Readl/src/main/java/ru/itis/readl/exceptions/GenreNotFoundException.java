package ru.itis.readl.exceptions;

public class GenreNotFoundException extends ReadlNotFoundException{

    public GenreNotFoundException() {
        super("Genre not found");
    }

}
