package com.company.springbootbankingapplication.model;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(name= "username")
private String username;

@Column(name ="accountnumber")
    private String accountNumber;

@Column(name="amount")
    private double amount;
@Column(name="type")
    private String type;
@Column(name="created_at")
    private Instant createdAt;

public Transaction() {}
    public Transaction(String username, String accountNumber, double amount, String type, Instant createdAt) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }

    public long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
