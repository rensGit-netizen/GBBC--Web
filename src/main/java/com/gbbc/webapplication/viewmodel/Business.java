package com.gbbc.webapplication.viewmodel;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Domain class for Business Objects
 * Holds information for:
 * smesView.jsp
 * sector.jsp
 *
 * @author R. Portzgen
 */
public class Business {
    private int id;
    private int customer_id;
    private String title;
    private String iban;
    private String saldo;
    private String first_name;
    private String last_name;
    private String user_name;
    private String sector;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
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

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        String saldoString = saldo.toString();
        DecimalFormat df = new DecimalFormat("##,###,###.00");
        saldoString = df.format(Double.valueOf(saldoString));
        this.saldo = saldoString;
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


    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
