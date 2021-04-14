package com.gbbc.webapplication.beans;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private double amount;
    private String description;
    @ManyToOne
    @JoinColumn(name = "debet_account_id")
    private BankAccount debetAccount;
    @ManyToOne
    @JoinColumn(name = "credit_account_id")
    private BankAccount creditAccount;
    private Timestamp date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BankAccount getDebetAccount() {
        return debetAccount;
    }

    public void setDebetAccount(BankAccount debetAccount) {
        this.debetAccount = debetAccount;
    }

    public BankAccount getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(BankAccount creditAccount) {
        this.creditAccount = creditAccount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
