package com.company.springbootbankingapplication.service;

import com.company.springbootbankingapplication.model.Customer;
import com.company.springbootbankingapplication.model.Transaction;
import com.company.springbootbankingapplication.repository.CustomerRepository;
import com.company.springbootbankingapplication.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.List;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository txRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Transactional
    public void deposit(String username, String accNum, double amount) {
        Customer c = customerRepo.findById(accNum).orElseThrow(() -> new RuntimeException("Account not found"));
        c.setBalance(c.getBalance() + amount);
        customerRepo.save(c);
        txRepo.save(new Transaction(username, accNum, amount, "Deposit", Instant.now()));
        System.out.println("Saved transaction: " + txRepo);

    }

    @Transactional
    public void withdraw(String username, String accNum, double amount) {
        Customer c = customerRepo.findById(accNum).orElseThrow(() -> new RuntimeException("Account not found"));
        if (c.getBalance() < amount) throw new RuntimeException("Insufficient balance");
        c.setBalance(c.getBalance() - amount);
        customerRepo.save(c);
        txRepo.save(new Transaction(username, accNum, amount, "Withdrawal", Instant.now()));
    }

    public List<Transaction> getHistory(String username, String accNum) {
        return txRepo.findByUsernameAndAccountNumberOrderByCreatedAtDesc(username, accNum);
    }
}
