package ru.itis.readl.services;

import ru.itis.readl.models.Account;

public interface VkSignInService {
    Account signIn(String code);
    String getAuthorizationPath();
}
