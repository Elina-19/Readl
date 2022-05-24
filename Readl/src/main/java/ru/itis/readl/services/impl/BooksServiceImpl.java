package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.readl.dto.forms.AddBookForm;
import ru.itis.readl.dto.BookDto;
import ru.itis.readl.dto.ExtendedBookDto;
import ru.itis.readl.dto.requests.SearchBookRequest;
import ru.itis.readl.exceptions.BookNotFoundException;
import ru.itis.readl.models.*;
import ru.itis.readl.repositories.BooksRepository;
import ru.itis.readl.filter.specifications.Specifications;
import ru.itis.readl.services.AccountsService;
import ru.itis.readl.services.BooksService;
import ru.itis.readl.services.FileInfoService;
import ru.itis.readl.services.GenresService;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

import static ru.itis.readl.dto.BookDto.*;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

    private final BooksRepository booksRepository;

    private AccountsService accountsService;
    private final FileInfoService fileInfoService;
    private final GenresService genresService;

    @Override
    public List<BookDto> findAll() {
        return from(booksRepository.findAll());
    }

    @Override
    public List<BookDto> search(String search) {
        return from(booksRepository
                .findAllByNameLike(search.toLowerCase()));
    }

    @Override
    public List<BookDto> getBooksBySearchRequest(SearchBookRequest searchBookRequest) {
        return from(booksRepository
                .findAll(addSearchCriteria(searchBookRequest))
                .stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    private Specification<Book> addSearchCriteria(SearchBookRequest searchRequest){
        List<Specification<Book>> specifications = new ArrayList<>();

        if (searchRequest.getName() != null){
            specifications.add(Specifications.like(
                    Book_.NAME, searchRequest.getName()));
        }

        if (searchRequest.getGenres() != null) {
            specifications.add(Specifications.in(
                    JoinType.INNER, Book_.GENRES + "." + Genre_.ID, Arrays.stream(searchRequest.getGenres())
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())));
        }

        return specifications.stream()
                .reduce(Specification::and)
                .orElse(null);
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

    @Override
    public List<BookDto> findByGenre(String genre) {
        return from(booksRepository.findByGenre(genre));
    }

    private Set<Genre> getGenres(String[] idGenres){
        Set<Genre> genres = new HashSet<>();

        if (idGenres == null){
            return genres;
        }

        for (String genre: idGenres){
            genres.add(genresService
                    .getGenreById(Integer.parseInt(genre)));
        }

        return genres;
    }

    @Autowired
    public void setAccountsService(AccountsService accountsService) {
        this.accountsService = accountsService;
    }
}
