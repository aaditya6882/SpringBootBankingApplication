package com.company.springbootbankingapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(
                                        "/",
                                        "/oauth2/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/api/customers/oauth2/success"
                                ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/customers/createAccount").hasRole("ADMIN")
                        .requestMatchers("/api/customers/login").permitAll()
                        .requestMatchers("/api/customers/*/balance", "/api/transactions/history")
                        .hasRole("CUSTOMER")
                        .requestMatchers("/api/customers/**", "/api/transactions/**")
                        .hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        )
                        .defaultSuccessUrl("http://localhost:5173/oauth2/redirect", true)

                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

}
