package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.Review;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByBookAndDateAfter(Book book, LocalDateTime date);
}
