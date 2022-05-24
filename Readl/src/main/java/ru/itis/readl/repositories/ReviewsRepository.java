package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    List<Review> findAllByBookIdAndIsDeletedAndDateAfter(long bookId, Boolean isDeleted, LocalDateTime date);

    Optional<Review> findAllByIdAndAuthorIdAndIsDeleted(Long id, Long authorId, Boolean isDeleted);

    List<Review> findAllByBookIdAndIsDeleted(Long bookId, Boolean isDeleted);

}
