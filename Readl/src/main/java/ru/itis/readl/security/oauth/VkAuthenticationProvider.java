package ru.itis.readl.security.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class VkAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService vkAccountUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VkAuthentication vkAuthentication = (VkAuthentication) authentication;

        UserDetails userDetails = vkAccountUserDetailsService
                .loadUserByUsername(vkAuthentication.getName());
        vkAuthentication.setAuthenticated(true);
        vkAuthentication.setUserDetails(userDetails);

        log.debug("OAuth with VK is successful");

        return vkAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return VkAuthentication.class.equals(authentication);
    }
}
