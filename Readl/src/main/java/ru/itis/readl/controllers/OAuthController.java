package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class OAuthController {

    @GetMapping("/vk")
    public String signInWithVk(){
        return "redirect:/profile";
    }

}
