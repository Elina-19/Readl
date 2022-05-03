package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.readl.dto.forms.SignUpForm;
import ru.itis.readl.models.Account;
import ru.itis.readl.repositories.AccountsRepository;
import ru.itis.readl.services.SignUpService;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm) {
        accountsRepository.save(Account.builder()
                .nickname(signUpForm.getNickname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(
                        signUpForm.getPassword()))
                .build());
    }

}
