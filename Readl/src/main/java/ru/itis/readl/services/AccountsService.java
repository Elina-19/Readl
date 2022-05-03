package ru.itis.readl.services;

import ru.itis.readl.dto.BookDto;
import ru.itis.readl.models.Account;

import java.util.List;

public interface AccountsService {
    void save(Account account);
    Account getById(Long id);
    boolean exists(String email);
    boolean isFavourite(Long bookId, Long accountId);
    void addBookToFavourites(Long accountId, Long bookId);
    void deleteBookFromFavourites(Long accountId, Long bookId);
    List<BookDto> getFavourites(Long accountId);
    List<BookDto> getAuthorBooks(Long accountId);
}
