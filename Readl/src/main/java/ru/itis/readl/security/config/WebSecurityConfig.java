package ru.itis.readl.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService accountUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .rememberMe()
                    .alwaysRemember(true)
                    .tokenRepository(tokenRepository())
                    .tokenValiditySeconds(60*60*24*30)
                    .and()
                .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/signIn")
                    .defaultSuccessUrl("/profile")
                    .failureUrl("/signIn?error")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/signIn?logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/errors/forbidden");

    }

    @Bean
    public PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        return tokenRepository;
    }
}
