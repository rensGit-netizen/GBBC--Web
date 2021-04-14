package com.gbbc.webapplication.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Emily Koo
 */
@Entity
public class AuthorizedPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String first_name;
    private String last_name;
    private String user_name;
    private String password;
    @OneToMany
    private List<BankAccount> bankAccount = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BankAccount> getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(List<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }
}
