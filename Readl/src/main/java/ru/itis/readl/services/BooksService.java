package ru.itis.readl.services;

import ru.itis.readl.dto.forms.AddBookForm;
import ru.itis.readl.dto.BookDto;
import ru.itis.readl.dto.ExtendedBookDto;
import ru.itis.readl.dto.requests.SearchBookRequest;
import ru.itis.readl.models.Account;
import ru.itis.readl.models.Book;

import java.util.List;

public interface BooksService {

    List<BookDto> findAll();

    List<BookDto> search(String search);
    List<BookDto> getBooksBySearchRequest(SearchBookRequest searchBookRequest);
    List<BookDto> findByGenre(String genre);

    void save(Long authorId, AddBookForm bookForm);

    BookDto getBookDtoById(Long id);
    Book getById(Long id);

    ExtendedBookDto getExtendedBook(Account account, Long bookId);

}
