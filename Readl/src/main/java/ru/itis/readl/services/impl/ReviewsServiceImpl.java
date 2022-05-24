package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.readl.dto.ReviewDto;
import ru.itis.readl.dto.forms.AddReviewForm;
import ru.itis.readl.exceptions.ReviewNotFoundException;
import ru.itis.readl.models.Account;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.Review;
import ru.itis.readl.repositories.ReviewsRepository;
import ru.itis.readl.services.AccountsService;
import ru.itis.readl.services.BooksService;
import ru.itis.readl.services.ReviewsService;

import java.time.LocalDateTime;
import java.util.List;

import static ru.itis.readl.dto.ReviewDto.*;

@RequiredArgsConstructor
@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;

    private final BooksService booksService;
    private final AccountsService accountsService;

    @Override
    public List<ReviewDto> getReviews(Long bookId) {
        return from(reviewsRepository
                .findAllByBookIdAndIsDeleted(bookId, false));
    }

    @Override
    public Review getById(Long id) {
        return reviewsRepository
                .findByIdAndIsDeleted(id, false)
                .orElseThrow(ReviewNotFoundException::new);
    }

    @Override
    public List<ReviewDto> getReviewsAfterDate(Long bookId, LocalDateTime date) {
        return from(reviewsRepository
                .findAllByBookIdAndIsDeletedAndDateAfter(bookId, false, date));
    }

    @Override
    public ReviewDto save(Long authorId, AddReviewForm reviewDto) {
        Account account = accountsService.getById(authorId);
        Book book = booksService.getById(reviewDto.getBookId());

        return from(reviewsRepository.save(
                Review.builder()
                        .author(account)
                        .content(reviewDto.getContent())
                        .book(book)
                        .isDeleted(false)
                        .build()));
    }

    @Override
    public ReviewDto update(Long authorId, Long reviewId, AddReviewForm reviewForm) {
        Review review = getByIdAndAuthorId(reviewId, authorId);
        review.setContent(reviewForm.getContent());

        return from(reviewsRepository.save(review));
    }

    @Override
    public void delete(Long authorId, Long reviewId) {
        Review review = getByIdAndAuthorId(authorId, reviewId);
        review.setIsDeleted(true);

        reviewsRepository.save(review);
    }

    public Review getByIdAndAuthorId(Long id, Long authorId){
        return reviewsRepository.findAllByIdAndAuthorIdAndIsDeleted(id, authorId, false)
                .orElseThrow(ReviewNotFoundException::new);
    }
}
