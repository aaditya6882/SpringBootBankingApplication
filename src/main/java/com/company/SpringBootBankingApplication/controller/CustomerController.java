
package com.company.SpringBootBankingApplication.controller;

import com.company.SpringBootBankingApplication.Service.CustomerService;
import com.company.SpringBootBankingApplication.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody Customer c){
        customerService.createAccount(c);
        return ResponseEntity.ok("Account Created");
    }

    @PutMapping("/{accNum}")
    public ResponseEntity updateAccount(@PathVariable String accNum, @RequestBody Customer c){
        if(accNum.equals(c.getAccountNumber())){
        customerService.modifyAccount(c);
        return ResponseEntity.ok("Account Updated");
    }
    return ResponseEntity.badRequest().body("Account Not Found");
    }
    @PutMapping("/{accountNumber}/disable")
    public ResponseEntity<?> disable(@PathVariable String accountNumber) {
        customerService.disableAccount(accountNumber);
        return ResponseEntity.ok("Disabled");
    }
    @PutMapping("/{accountNumber}/enable")
    public ResponseEntity<?> enable(@PathVariable String accountNumber) {
        customerService.enableAccount(accountNumber);
        return ResponseEntity.ok("Enabled");
    }
    @GetMapping("/{userName}/balance")
    public ResponseEntity balance(@PathVariable String userName) {
        double b= customerService.getBalance(userName);
        return ResponseEntity.ok(b);

    }

    @GetMapping("/{userName}/search")
        public ResponseEntity<Optional<Customer>> search(@PathVariable String userName){
            Optional<Customer> l=customerService.findByUsername(userName);
            return ResponseEntity.ok(l);
        }
}
