
package com.company.SpringBootBankingApplication.controller;

import com.company.SpringBootBankingApplication.Service.CustomerService;
import com.company.SpringBootBankingApplication.model.Customer;
import com.company.SpringBootBankingApplication.model.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@RequestBody @Valid Customer c){
        Customer account = customerService.createAccount(c);
        if(account != null){
            return ResponseEntity.ok("Account Created");
        }
        return ResponseEntity.badRequest().build();
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

    @GetMapping("/{accountNumber}/search")
        public ResponseEntity<Optional<Customer>> search(@PathVariable String accountNumber){
            Optional<Customer> l=customerService.findByAccountNumber(accountNumber);
            return ResponseEntity.ok(l);
        }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Hardcoded admin
        if ("admin".equals(request.getUsername()) && "admin123".equals(request.getPassword())) {
            Map<String, Object> res = new HashMap<>();
            res.put("username", "admin");
            res.put("role", "ADMIN");
            res.put("accountNumber", "0000");
            return ResponseEntity.ok(res);
        }

        // Check customer in DB
        Optional<Customer> customerOpt = customerService.findByUsername(request.getUsername());
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
                Map<String, Object> res = new HashMap<>();
                res.put("username", customer.getUsername());
                res.put("role", customer.getRole());
                res.put("accountNumber", customer.getAccountNumber());
                return ResponseEntity.ok(res);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Invalid username or password");
    }
    @GetMapping("/oauth2/success")
    public ResponseEntity<?> oauth2Success(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("OAuth2User is null");

        String email = oAuth2User.getAttribute("email");
        System.out.println("OAuth2 Email: " + email);

        Optional<Customer> customerOpt = customerService.findByUsername(email);
        System.out.println("Customer found: " + customerOpt.isPresent());

        if (customerOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not registered");
        }

        Customer customer = customerOpt.get();
        Map<String, Object> res = new HashMap<>();
        res.put("username", customer.getUsername());
        res.put("role", customer.getRole());
        res.put("accountNumber", customer.getAccountNumber());

        return ResponseEntity.ok(res);
    }


}
