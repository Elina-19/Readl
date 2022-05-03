package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.itis.readl.dto.ReviewDto;
import ru.itis.readl.dto.forms.AddReviewForm;
import ru.itis.readl.security.details.AccountUserDetails;
import ru.itis.readl.services.ReviewsService;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getReviews(@RequestParam("id") Long bookId,
                                                      @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date){
        return ResponseEntity.ok(
                reviewsService.getReviewsAfterDate(bookId, date));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<ReviewDto> addReview(@AuthenticationPrincipal AccountUserDetails account,
                                               @Valid @RequestBody AddReviewForm reviewDto){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewsService.save(account.getAccount().getId(), reviewDto));

    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{review-id}")
    public ResponseEntity<ReviewDto> update(@PathVariable("review-id") Long reviewId,
                                            @Valid @RequestBody AddReviewForm reviewDto){

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(reviewsService.update(reviewId, reviewDto));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{review-id}")
    public ResponseEntity<?> delete(@PathVariable("review-id") Long reviewId){
        reviewsService.delete(reviewId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
