package com.company.SpringBootBankingApplication.Service;
import com.company.SpringBootBankingApplication.model.Customer;
import com.company.SpringBootBankingApplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Special case for hardcoded admin user
        if ("admin".equals(username)) {
            return new User(
                    "admin",
                    passwordEncoder().encode("admin123"),
                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))
            );
        }

        // Look up customer from DB
        Customer customer = customerRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!customer.isActive()) {
            throw new UsernameNotFoundException("User not active");
        }

        return new User(
                customer.getUsername(),
                customer.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"))
        );
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
