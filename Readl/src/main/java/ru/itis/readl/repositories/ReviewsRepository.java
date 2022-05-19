package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByBookAndDateAfter(Book book, LocalDateTime date);

    Optional<Review> findAllByIdAndAuthorId(Long id, Long authorId);

}
