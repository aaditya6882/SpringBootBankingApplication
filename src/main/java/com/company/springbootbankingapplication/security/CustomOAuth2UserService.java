package com.company.springbootbankingapplication.security;

import com.company.springbootbankingapplication.model.Customer;
import com.company.springbootbankingapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final CustomerService customerService;

    @Autowired
    public CustomOAuth2UserService(@Lazy CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // Load user from Google
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        if (email == null) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        // Check if user exists in your DB
        Optional<Customer> existingUser = customerService.findByUsername(email);

        if (existingUser.isEmpty()) {
            // If user not registered, deny login
            throw new OAuth2AuthenticationException("User not registered in the system");
        }

        Customer customer = existingUser.get();

        // Map role from DB to Spring Security authority
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + customer.getRole());

        // Return DefaultOAuth2User with authorities, attributes, and key "email"
        return new DefaultOAuth2User(
                Collections.singleton(authority),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}
