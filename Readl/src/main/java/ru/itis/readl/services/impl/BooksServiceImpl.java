package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.readl.dto.forms.AddBookForm;
import ru.itis.readl.dto.BookDto;
import ru.itis.readl.dto.ExtendedBookDto;
import ru.itis.readl.exceptions.BookNotFoundException;
import ru.itis.readl.models.Account;
import ru.itis.readl.models.Book;
import ru.itis.readl.models.FileInfo;
import ru.itis.readl.models.Genre;
import ru.itis.readl.repositories.BooksRepository;
import ru.itis.readl.services.AccountsService;
import ru.itis.readl.services.BooksService;
import ru.itis.readl.services.FileInfoService;

import java.util.*;

import static ru.itis.readl.dto.BookDto.*;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;

    private AccountsService accountsService;
    private final FileInfoService fileInfoService;

    @Override
    public List<BookDto> findAll() {
        return from(booksRepository.findAll());
    }

    @Override
    public List<BookDto> search(String search) {
        return from(booksRepository
                .findAllByNameLike(search.toLowerCase()));
    }

    @Transactional
    @Override
    public void save(Long authorId, AddBookForm bookForm) {
        Account account = accountsService.getById(authorId);

        FileInfo fileInfo = fileInfoService.upload(bookForm.getFile());

        booksRepository.save(Book.builder()
                .author(account)
                .name(bookForm.getName())
                .description(bookForm.getDescription())
                .numberOfRates(0)
                .rate(0.0)
                .genres(getGenres(bookForm.getGenres()))
                .image(fileInfo)
                .build());
    }

    @Override
    public BookDto getBookDtoById(Long id) {
        return from(getById(id));
    }

    @Override
    public Book getById(Long id) {
        return booksRepository
                .findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @Override
    public ExtendedBookDto getExtendedBook(Account account, Long bookId) {
        ExtendedBookDto bookDto = ExtendedBookDto.extendedFrom(
                        booksRepository.findById(bookId)
                        .orElseThrow(BookNotFoundException::new));

        if (account != null){
            bookDto.setIsFavourite(
                    accountsService.isFavourite(bookId, account.getId()));
        }

        return bookDto;
    }

    private Set<Genre> getGenres(String[] idGenres){
        Set<Genre> genres = new HashSet<>();

        if (idGenres == null){
            return genres;
        }

        for (String genre: idGenres){
            genres.add(Genre.builder()
                    .id(Integer.valueOf(genre))
                    .build());
        }

        return genres;
    }

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }
}
