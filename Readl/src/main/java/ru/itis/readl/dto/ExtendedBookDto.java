package ru.itis.readl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ru.itis.readl.models.Account;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.Chapter;
import ru.itis.readl.models.Genre;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExtendedBookDto extends BookDto{

    private Boolean isFavourite;

    private Timestamp dateOfAdding;

    private Set<Genre> genres;

    private Set<Chapter> chapters;

    private Account author;

    public static ExtendedBookDto extendedFrom(Book book){
        ExtendedBookDto bookDto = ExtendedBookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .description(book.getDescription())
                .chapters(book.getChapters())
                .genres(book.getGenres())
                .dateOfAdding(book.getDateOfAdding())
                .author(book.getAuthor())
                .build();

        if (book.getImage() != null){
            bookDto.setFileStorageName(book.getImage().getStorageFileName());
        }

        return bookDto;
    }

    public static List<ExtendedBookDto> extendedFrom(Collection<Book> books){
        return books.stream()
                .map(ExtendedBookDto::extendedFrom).collect(Collectors.toList());
    }

}
