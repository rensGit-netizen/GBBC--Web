package com.gbbc.webapplication.beans;

import javax.persistence.*;

/**
 * @Entity bean for Account Request; Request for connection customers to accounts
 * @author R. Portzgen
 */
@Entity
public class AccountRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne
    private BankAccount bankAccount;
    @OneToOne
    private Customer customer;
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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
}
