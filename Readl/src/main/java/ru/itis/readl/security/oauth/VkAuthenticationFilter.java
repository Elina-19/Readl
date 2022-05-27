package ru.itis.readl.security.oauth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.readl.services.VkSignInService;
import ru.itis.readl.utils.VkUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class VkAuthenticationFilter extends OncePerRequestFilter {

    private final static RequestMatcher authRequest = new AntPathRequestMatcher("/auth/vk", "GET");

    private final VkSignInService vkSignInService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (authRequest.matches(request)) {
            log.debug("Attempt of oAuth with VK");

            String code = request.getParameter("code");

            if (code != null) {
                VkAuthentication authentication = new VkAuthentication(code);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else {
                response.sendRedirect(vkSignInService.getAuthorizationPath());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
