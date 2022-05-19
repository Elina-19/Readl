package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.readl.dto.BookDto;
import ru.itis.readl.dto.forms.AddBookForm;
import ru.itis.readl.dto.requests.SearchBookRequest;
import ru.itis.readl.security.details.AccountUserDetails;
import ru.itis.readl.services.BooksService;
import ru.itis.readl.services.GenresService;
import ru.itis.readl.utils.UserDetailsHelper;

import javax.activation.MimeType;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/books")
public class BookController {

    private final BooksService booksService;
    private final GenresService genresService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getAddBookPage(Model model){
        model.addAttribute("genres", genresService.findAll());
        model.addAttribute("addBookForm", new AddBookForm());

        return "addBook";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public String addBook(@AuthenticationPrincipal AccountUserDetails account,
                          @Valid AddBookForm addBookForm, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("genres", genresService.findAll());
            model.addAttribute("addBookForm", addBookForm);
            return "addBook";
        }

        booksService.save(account.getAccount().getId(), addBookForm);
        return "redirect:/profile";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String search, Model model){
        model.addAttribute("books", booksService.search(search));

        return "main";
    }

    @GetMapping("/{book-id}")
    public String getBookPage(@PathVariable("book-id") Long id,
                              @AuthenticationPrincipal AccountUserDetails account, Model model){

        //TODO: Брать id на фронте через теги
        if (account != null){
            model.addAttribute("currentUserId", account.getAccount().getId());
        }

        model.addAttribute("book", booksService
                        .getExtendedBook(UserDetailsHelper
                        .getAccount(account), id));

        return "book";
    }

    @GetMapping("/{book-id}/reviews")
    public String getReviewsPage(@PathVariable("book-id") Long bookId, Model model){
        model.addAttribute("bookId", bookId);

        return "reviews";
    }

    @GetMapping(value = "/genre", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDto>> getBooksByGenre(@RequestParam("genre") String genre){
        return ResponseEntity.ok()
                .body(booksService.findByGenre(genre));
    }

    @PostMapping("/search")
    public String getBooksBySearchRequest(SearchBookRequest searchBookRequest, Model model){
        model.addAttribute("books", booksService.getBooksBySearchRequest(searchBookRequest));

        return "main";
    }
}
