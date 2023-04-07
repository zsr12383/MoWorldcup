package com.example.moworldcup.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/favicon.ico");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()
//            .headers().frameOptions().disable()
//            .and()
//            .formLogin().disable()
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/api/v1/topic/**", "/h2-console/**", "/profile").permitAll()
                .anyRequest().authenticated()
            )
            // .requestMatchers("/api/v1/**")
            // .hasRole(Role.USER.name())
//            .formLogin((form) -> form
//                .loginPage("/login")
//                .permitAll()
//            )
            .logout()
            .logoutSuccessUrl("/")
            .and()
//            .oauth2Login(withDefaults());
            .oauth2Login()
            .userInfoEndpoint()
            .userService(customOAuth2UserService);
        return http.build();
    }
}