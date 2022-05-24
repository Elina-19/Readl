package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.readl.dto.BookDto;
import ru.itis.readl.exceptions.AccountNotFoundException;
import ru.itis.readl.models.Account;
import ru.itis.readl.models.Book;
import ru.itis.readl.repositories.AccountsRepository;
import ru.itis.readl.services.AccountsService;
import ru.itis.readl.services.BooksService;

import java.util.List;

import static ru.itis.readl.dto.BookDto.*;

@RequiredArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService {

    private final AccountsRepository accountsRepository;

    private BooksService booksService;

    @Override
    public void save(Account account) {

    }

    @Override
    public Account getById(Long id) {
        return accountsRepository.findById(id)
                .orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public boolean exists(String email) {
        return accountsRepository.existsAccountByEmail(email);
    }

    @Override
    public boolean isFavourite(Long bookId, Long accountId) {
        return accountsRepository.existsAccountByIdAndFavouriteBooksContains(accountId, Book.builder()
                .id(bookId)
                .build());
    }

    @Transactional
    @Override
    public void addBookToFavourites(Long accountId, Long bookId) {
        Account account = getById(accountId);
        Book book = booksService.getById(bookId);

        account.getFavouriteBooks().add(book);
        accountsRepository.save(account);
    }

    @Transactional
    @Override
    public void deleteBookFromFavourites(Long accountId, Long bookId) {
        Account account = getById(accountId);
        Book book = booksService.getById(bookId);

        account.getFavouriteBooks().remove(book);
        accountsRepository.save(account);
    }

    @Override
    public List<BookDto> getFavourites(Long accountId) {
        return from(getById(accountId)
                .getFavouriteBooks());
    }

    @Override
    public List<BookDto> getAuthorBooks(Long accountId) {
        return from(getById(accountId)
                .getAuthorBooks());
    }

    @Autowired
    public void setBooksService(BooksService booksService) {
        this.booksService = booksService;
    }
}
