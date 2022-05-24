package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itis.readl.dto.forms.AddChapterForm;
import ru.itis.readl.services.ChaptersService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chapter")
public class ChaptersController {

    private final ChaptersService chaptersService;

    @GetMapping("/{chapter-id}")
    public String getChapterPage(@PathVariable("chapter-id") Long id, Model model){
        model.addAttribute("chapter", chaptersService.getChapterById(id));

        return "chapter";
    }

    @GetMapping
    public String getAddChapterPage(@RequestParam("id") Long bookId, Model model){
        model.addAttribute("bookId", bookId);
        model.addAttribute("addChapterForm", new AddChapterForm());

        return "addChapter";
    }

    @PostMapping
    public String addChapter(@RequestParam("book-id") Long bookId, Model model,
                             @Valid AddChapterForm chapterForm, BindingResult result){

        if (result.hasErrors()){
            model.addAttribute("addChapterForm", chapterForm);
            model.addAttribute("bookId", bookId);

            return "addChapter";
        }

        chaptersService.save(bookId, chapterForm);

        return "redirect:/books/" + bookId;
    }
}
