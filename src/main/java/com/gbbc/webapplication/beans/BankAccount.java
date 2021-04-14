package com.gbbc.webapplication.beans;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Entity bean for Bank Account Data
 * @author E. Koo, R. Portzgen
 */

@Entity
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String iban;
    private BigDecimal saldo;
    //keuzes: Zakelijk, Prive
    private String type;
    //keuzes: prive, Horeca, Gezondheidszorg, Handel en Dienstverlening, ICT, Landbouw, Onderwijs, Techniek, Transport, Toerisme, Cultuur
    private String sector;
    @OneToMany(mappedBy = "debetAccount")
    private List<Transaction> transactionHistorydebit;
    @OneToMany(mappedBy = "creditAccount")
    private List<Transaction> transactionHistorycredit;
    @ManyToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER)
    private List<Customer> customer = new ArrayList<>();
    @ManyToOne
    private Customer gemachtigde;
    @ManyToOne
    private AuthorizedPerson authorizedPerson;
    private int pinNumber;


    public BankAccount() {
    }

    public AuthorizedPerson getAuthorizedPerson() {
        return authorizedPerson;
    }

    public void setAuthorizedPerson(AuthorizedPerson authorizedPerson) {
        this.authorizedPerson = authorizedPerson;
    }

    public void setGemachtigde(Customer gemachtigde) {
        this.gemachtigde = gemachtigde;
    }

    public Customer getGemachtigde() {
        return gemachtigde;
    }


    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    public void addCustomer(Customer customer){
        this.customer.add(customer);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public List<Transaction> getTransactionHistorydebit() {
        return transactionHistorydebit;
    }

    public void setTransactionHistorydebit(List<Transaction> transactionHistorydebit) {
        this.transactionHistorydebit = transactionHistorydebit;
    }

    public List<Transaction> getTransactionHistorycredit() {
        return transactionHistorycredit;
    }

    public void setTransactionHistorycredit(List<Transaction> transactionHistorycredit) {
        this.transactionHistorycredit = transactionHistorycredit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
}
