package ru.itis.readl.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.readl.services.VkSignInService;

@RequiredArgsConstructor
@Service
public class VkAccountUserDetailsService implements UserDetailsService {

    private final VkSignInService vkSignInService;

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        return new AccountUserDetails(
                vkSignInService.signIn(code));
    }
}
