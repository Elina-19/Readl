package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.readl.dto.VkAccountDto;
import ru.itis.readl.dto.VkToken;
import ru.itis.readl.exceptions.VkAuthException;
import ru.itis.readl.models.Account;
import ru.itis.readl.repositories.AccountsRepository;
import ru.itis.readl.services.VkSignInService;
import ru.itis.readl.utils.VkUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VkSignInServiceImpl implements VkSignInService {

    private final AccountsRepository accountsRepository;

    private final VkUtils vkUtils;

    @Override
    public Account signIn(String code) {
        VkToken vkToken = vkUtils.getAuthToken(code);

        if (vkToken.getEmail() == null){
            throw new VkAuthException();
        }

        Optional<Account> optionalAccount = accountsRepository
                .findByEmail(vkToken.getEmail());

        if (optionalAccount.isPresent()){
            return optionalAccount.get();
        }

        VkAccountDto vkAccountDto = vkUtils.getVkAccountDto(vkToken);

        if (vkAccountDto == null){
            throw new VkAuthException();
        }

        Account account = Account.builder()
                .nickname(vkAccountDto.getFirstName())
                .email(vkToken.getEmail())
                .build();

        return accountsRepository.save(account);
    }

    @Override
    public String getAuthorizationPath() {
        return vkUtils.getAuthorizeUrl();
    }
}
