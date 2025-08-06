package com.company.SpringBootBankingApplication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "account_number", length = 50)
    private String accountNumber;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name="username" ,nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password; // NOTE: store hashed in production!

    @Column(nullable = false)
    private double balance;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public Customer() {}

    public Customer(String accountNumber, String firstName, String lastName, String username, String password, double balance) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.password = password;
        this.balance = balance;
        this.isActive = true;
    }

    // Getters and setters (generated)
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
    public void setIsActive(boolean active) { isActive = active; }
}
