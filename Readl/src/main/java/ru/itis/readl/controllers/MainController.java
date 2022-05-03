package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.readl.services.BooksService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/main")
public class MainController {

    private final BooksService booksService;

    @GetMapping
    public String getMainPage(Model model){
        model.addAttribute("books", booksService.findAll());

        return "main";
    }
}
