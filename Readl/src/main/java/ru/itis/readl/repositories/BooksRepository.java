package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.readl.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @Query("select book from Book book where name like %:name%")
    List<Book> findAllByNameLike(String name);

}
