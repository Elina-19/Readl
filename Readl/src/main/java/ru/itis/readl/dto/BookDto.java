package ru.itis.readl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.readl.models.Book;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BookDto {

    private Long id;

    private String name;

    private String description;

    private String fileStorageName;

    public static BookDto from(Book book){
        BookDto bookDto = BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .build();

        if (book.getImage() != null){
            bookDto.setFileStorageName(book.getImage().getStorageFileName());
        }

        return bookDto;
    }

    public static List<BookDto> from(Collection<Book> books){
        return books.stream()
                .map(BookDto::from)
                .collect(Collectors.toList());
    }
}
