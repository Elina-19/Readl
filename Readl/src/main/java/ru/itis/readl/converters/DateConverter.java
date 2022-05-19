package ru.itis.readl.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        try {
            return LocalDateTime.parse(source, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException(exception);
        }
    }
}
