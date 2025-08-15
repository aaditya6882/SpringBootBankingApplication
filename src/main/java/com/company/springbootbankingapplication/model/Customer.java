package com.company.springbootbankingapplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "account_number", length = 50)
    @NotBlank(message = "Account number cannot be blank")
    private String accountNumber;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Column(name = "username", nullable = false, unique = true)
    @JsonProperty("username")
    @NotBlank(message = "Username cannot be blank")
    private String userName;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "balance")
    @Min(value = 0, message = "Balance cannot be negative")
    private double balance;

    @Column(name="is_active")
    private boolean isActive ;

    @Column(name="role", nullable=false)
    private String role;

    public Customer(String accountNumber, String firstName, String lastName, String username, String password, double balance, String role) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.password = password;
        this.balance = balance;
        this.isActive = true;
        this.role = role;
    }

    public Customer() {}

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return userName; }
    public void setUsername(String username) { this.userName = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
