package com.company.springbootbankingapplication.repository;

import com.company.springbootbankingapplication.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUsernameAndAccountNumberOrderByCreatedAtDesc(String username, String accountNumber);
}
