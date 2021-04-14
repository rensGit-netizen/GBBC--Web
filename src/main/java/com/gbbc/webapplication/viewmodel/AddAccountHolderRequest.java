package com.gbbc.webapplication.viewmodel;

/**
 * Domain class for Account Holder Requests
 * Holds information for:
 * addAccountHolder.jsp
 * linkAccountView.jsp
 *
 * @author R. Portzgen
 */
public class AddAccountHolderRequest {
    private String iban;
    private String customer_username;
    private int code;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
