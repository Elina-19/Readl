package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.readl.dto.forms.SignUpForm;
import ru.itis.readl.services.SignUpService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage(Authentication authentication, Model model){
        if (authentication != null){
            return "redirect:/main";
        }

        model.addAttribute("signUpForm", new SignUpForm());
        return "signUp";
    }

    @PostMapping
    public String signUp(@Valid SignUpForm form, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("signUpForm", form);
            return "signUp";
        }

        signUpService.signUp(form);
        return "redirect:/signIn";
    }
}
