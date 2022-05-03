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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.itis.readl.dto.ReviewDto.*;

@RequiredArgsConstructor
@Service
public class ReviewsServiceImpl implements ReviewsService {

    //TODO: обработать отдачу удаленных рецензий, переписать стримы на запросы в БД

    private final ReviewsRepository reviewsRepository;

    private final BooksService booksService;
    private final AccountsService accountsService;

    @Override
    public List<ReviewDto> getReviews(Long bookId) {

        return from(booksService
                .getById(bookId)
                .getReviews()
                .stream()
                .filter(e -> e.getIsDeleted() == false)
                .collect(Collectors.toList()));
    }

    @Override
    public Review getById(Long id) {
        return reviewsRepository
                .findById(id)
                .orElseThrow(ReviewNotFoundException::new);
    }

    @Override
    public List<ReviewDto> getReviewsAfterDate(Long bookId, LocalDateTime date) {
        Book book = booksService.getById(bookId);

        return from(reviewsRepository
                .findAllByBookAndDateAfter(book, date));

//        return from(booksService
//                .getById(bookId)
//                .getReviews()
//                .stream()
//                .filter(e -> e.getDate().after(date))
//                .collect(Collectors.toList()));
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
                        .build()));
    }

    @Override
    public ReviewDto update(Long reviewId, AddReviewForm reviewForm) {
        Review review = getById(reviewId);
        review.setContent(reviewForm.getContent());

        return from(reviewsRepository.save(review));
    }

    @Override
    public void delete(Long reviewId) {
        Review review = getById(reviewId);
        review.setIsDeleted(true);

        reviewsRepository.save(review);
    }
}
