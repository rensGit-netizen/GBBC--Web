/**
 * @author E. Koo, R. Portzgen
 */
package com.gbbc.webapplication.beans;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bsn;
    private String city;
    private String country;
    private String date_of_birth;
    private String first_name;
    private String last_name;
    private String password;
    private String postal_code;
    private String street;
    private String userName;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_has_bankaccount",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_account_id"))
    private List<BankAccount> bankAccount = new ArrayList<>();
    @OneToMany
    private List<BankAccount> authorizedBankAccount = new ArrayList<>();

    public Customer() {
    }

    public Customer(int id,  String first_name, String last_name, String userName) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.userName = userName;
    }

    public List<BankAccount> getAuthorizedBankAccount() {
        return authorizedBankAccount;
    }

    public void setAuthorizedBankAccount(List<BankAccount> authorizedBankAccount) {
        this.authorizedBankAccount = authorizedBankAccount;
    }

    public List<BankAccount> getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(List<BankAccount> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void addBankAccount(BankAccount bankAccount){
        this.bankAccount.add(bankAccount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
    }
}
