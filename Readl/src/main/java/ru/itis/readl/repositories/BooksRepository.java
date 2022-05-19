package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.itis.readl.models.Book;

import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Query("select book from Book book where name like %:name%")
    List<Book> findAllByNameLike(String name);

    @Query(nativeQuery = true, value =
            "WITH books_genre_id AS (\n" +
            "    SELECT book.*, b_g.genre_id\n" +
            "       FROM book\n" +
            "    LEFT JOIN book_genre b_g on book.id = b_g.book_id)\n" +
            "SELECT bgi.*\n" +
            "   FROM books_genre_id bgi\n" +
            "INNER JOIN genre ON genre.id = bgi.genre_id\n" +
            "WHERE genre.name = :genre")
    List<Book> findByGenre(String genre);

}
