package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.readl.security.details.AccountUserDetails;
import ru.itis.readl.services.AccountsService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class AccountsController {

    private final AccountsService accountsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String getProfilePage(@AuthenticationPrincipal AccountUserDetails account, Model model){
        model.addAttribute("account", account.getAccount());

        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/favourites")
    public String getFavourites(@AuthenticationPrincipal AccountUserDetails account, Model model){
        model.addAttribute("books", accountsService.getFavourites(
                account.getAccount().getId()));

        return "favourite";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/favourites")
    public void addBookToFavourites(@AuthenticationPrincipal AccountUserDetails account,
                                    @RequestParam("bookId") Long bookId){
        System.out.println("herebsvdanvk");

        accountsService.addBookToFavourites(account.getAccount().getId(), bookId);
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/favourites/{book-id}")
    public void deleteBookFromFavourites(@AuthenticationPrincipal AccountUserDetails account,
                                         @PathVariable("book-id") Long bookId){

        accountsService.deleteBookFromFavourites(account.getAccount().getId(), bookId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/books")
    public String getAuthorBooks(@AuthenticationPrincipal AccountUserDetails account, Model model){
        model.addAttribute("books", accountsService.getAuthorBooks(
                account.getAccount().getId()));

        return "myBooks";
    }
}
