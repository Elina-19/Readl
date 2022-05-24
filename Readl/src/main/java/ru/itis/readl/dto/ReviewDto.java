package ru.itis.readl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.readl.models.Review;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long id;

    private LocalDateTime date;

    private AccountDto author;

    private BookDto book;

    private String content;

    public static ReviewDto from(Review review){
        return ReviewDto.builder()
                .id(review.getId())
                .date(review.getDate())
                .content(review.getContent())
                .author(AccountDto.from(review.getAuthor()))
                .book(BookDto.from(review.getBook()))
                .build();
    }

    public static List<ReviewDto> from(Collection<Review> reviews){
        return reviews.stream()
                .map(ReviewDto::from)
                .collect(Collectors.toList());
    }
}
