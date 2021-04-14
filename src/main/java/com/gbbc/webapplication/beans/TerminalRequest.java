package com.gbbc.webapplication.beans;

import javax.persistence.*;
/**
 * @Entity bean for Terminal Requests; Request for Pin Terminal for particular Bank Account
 * @author R. Portzgen
 */
@Entity
public class TerminalRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(fetch = FetchType.EAGER)
    private BankAccount bankAccount;
    @OneToOne(fetch = FetchType.EAGER)
    private Customer customer;
    private String requestDate;
    private String responseDate;
    @Column(columnDefinition = "int default 0")
    private int controlCode;
    private String status;

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getControlCode() {
        return controlCode;
    }

    public void setControlCode(int controlCode) {
        this.controlCode = controlCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
