package ru.itis.readl.exceptions;

public class ChapterNotFoundException extends ReadlNotFoundException{
    public ChapterNotFoundException() {
        super("Chapter not found");
    }
}
