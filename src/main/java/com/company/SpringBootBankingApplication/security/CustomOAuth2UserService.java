package com.company.SpringBootBankingApplication.security;

import com.company.SpringBootBankingApplication.model.Customer; // ✅ Make sure this is present
import com.company.SpringBootBankingApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");

        // Fetch user from DB
        Customer customer = customerRepository.findByUserName(email)
                .orElseThrow(() -> new RuntimeException("User not registered. Please contact admin."));

        // Map DB role to Spring Security authority
        return new DefaultOAuth2User(
                Set.of(new SimpleGrantedAuthority(customer.getRole())),
                oAuth2User.getAttributes(),
                "email"
        );
    }
}
