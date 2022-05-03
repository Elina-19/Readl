package ru.itis.readl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.Chapter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterDto {

    private Long id;

    private String name;

    private Book book;

    private String content;

    public static ChapterDto from(Chapter chapter){
        return ChapterDto.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .book(chapter.getBook())
                .build();
    }

}
