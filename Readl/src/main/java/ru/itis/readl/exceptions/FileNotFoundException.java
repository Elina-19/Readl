package ru.itis.readl.exceptions;

public class FileNotFoundException extends ReadlNotFoundException{
    public FileNotFoundException() {
        super("File not found");
    }
}
