package ru.itis.readl.services;

import ru.itis.readl.dto.ReviewDto;
import ru.itis.readl.dto.forms.AddReviewForm;
import ru.itis.readl.models.Review;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ReviewsService {
    List<ReviewDto> getReviews(Long bookId);
    Review getById(Long id);
    List<ReviewDto> getReviewsAfterDate(Long bookId, LocalDateTime date);
    ReviewDto save(Long authorId, AddReviewForm reviewForm);
    ReviewDto update(Long reviewId, AddReviewForm reviewForm);
    void delete(Long reviewId);
}
