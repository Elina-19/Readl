package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.readl.security.details.AccountUserDetails;
import ru.itis.readl.services.VkSignInService;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class OAuthController {

    private final VkSignInService vkSignInService;

    @GetMapping("/vk")
    public String signInWithVk(){
        return "redirect:/profile";
    }

//    @GetMapping("/success")
//    public String signInWithVkSuccess(@RequestParam("code") String code, @AuthenticationPrincipal AccountUserDetails account){
//        account.setAccount(vkSignInService.signIn(code));
//        return "redirect:/profile";
//    }
}
