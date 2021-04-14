package com.gbbc.webapplication.viewmodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TransactionViewModel {
    private String creditIban;
    private String DebitIban;
    private String description;
    private String reciever;
    private double amount;
    private Timestamp date;
    private int bankId;
    private double totalAmount;

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public TransactionViewModel() {
        LocalDateTime localDate = LocalDateTime.now();
        this.date = Timestamp.valueOf(localDate);
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getCreditIban() {
        return creditIban;
    }

    public void setCreditIban(String creditIban) {
        this.creditIban = creditIban;
    }

    public String getDebitIban() {
        return DebitIban;
    }

    public void setDebitIban(String debitIban) {
        DebitIban = debitIban;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
