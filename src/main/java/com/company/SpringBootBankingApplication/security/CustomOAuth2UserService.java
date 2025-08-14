package com.company.SpringBootBankingApplication.security;

import com.company.SpringBootBankingApplication.model.Customer;
import com.company.SpringBootBankingApplication.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    @Lazy
    private CustomerService customerService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Extract email and name from Google
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        // Check if user already exists
        Optional<Customer> existingUser = customerService.findByUsername(email);

        if (existingUser.isEmpty()) {
            // Create new Customer if not exists
            Customer newUser = new Customer();
            newUser.setAccountNumber("G" + System.currentTimeMillis()); // unique account number
            newUser.setFirstName(name.split(" ")[0]);
            newUser.setLastName(name.contains(" ") ? name.split(" ")[1] : "");
            newUser.setUsername(email);
            newUser.setPassword(""); // no password needed for Google login
            newUser.setBalance(0);
            newUser.setRole("CUSTOMER");
            newUser.setActive(true);

            customerService.createAccount(newUser);
        }

        return oAuth2User;
    }
}
