package com.company.SpringBootBankingApplication.Service;
import com.company.SpringBootBankingApplication.model.Customer;
import com.company.SpringBootBankingApplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Service
public class CustomerService {
@Autowired
 private CustomerRepository repo;
public Customer createAccount(Customer c){
        if (repo.existsByUserName(c.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (repo.existsById(c.getAccountNumber())) {
            throw new IllegalArgumentException("Account number already exists");
        }
        c.setIsActive(true);
    return repo.save(c);
    }
    public Optional<Customer> findByAccountNumber(String accountNumber) {
        return repo.findById(accountNumber);
    }
    public Optional<Customer> findByUsername(String username) {
        return repo.findByUserName(username);
    }
    public double getBalance(String username) {
        Optional<Customer> c = repo.findByUserName(username);
        return c.map(Customer::getBalance).orElse(0.0);
    }

    public void modifyAccount(Customer updated) {
        Customer existing = repo.findById(updated.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        existing.setFirstName(updated.getFirstName());
        existing.setLastName(updated.getLastName());
        existing.setUsername(updated.getUsername());
        existing.setPassword(updated.getPassword());
        repo.save(existing);
    }

    public void disableAccount(String accountNumber) {
        Customer existing = repo.findById(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));
        existing.setIsActive(false);
        repo.save(existing);
    }

    public void enableAccount(String accountNumber) {
        Customer existing = repo.findById(accountNumber).orElseThrow(() -> new RuntimeException("Account not found"));
        existing.setIsActive(true);
        repo.save(existing);
    }
}
