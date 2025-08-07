package com.company.SpringBootBankingApplication.controller;

import com.company.SpringBootBankingApplication.Service.TransactionService;
import com.company.SpringBootBankingApplication.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transaction;
    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam String userName , @RequestParam String accNum, @RequestParam double amount) {
        transaction.deposit(userName, accNum, amount);
        return ResponseEntity.ok("Deposited Successfully");
    }
    @PostMapping("/Withdraw")
    public ResponseEntity withdraw(@RequestParam String userName , @RequestParam String accNum, @RequestParam double amount) {
        transaction.withdraw(userName, accNum, amount);
        return ResponseEntity.ok("withdrawn Successfully");
    }

    @GetMapping("/history")
    public ResponseEntity<List<Transaction>> history(@RequestParam String userName , @RequestParam String accNum) {
         List <Transaction > h=transaction.getHistory(userName, accNum);
        return   ResponseEntity.ok(h);
    }
}
