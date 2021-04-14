package com.gbbc.webapplication.viewmodel;
/**
 * Domain class for Pin Terminal Status objects
 * Holds information for:
 * bankEmployeeView.jsp
 * controlCodeDisplay.jsp
 *
 * @author R. Portzgen
 */
public class PinTerminalStatus {
    private int customerId;
    private String iban;
    private String user_name;
    private String first_name;
    private String last_name;
    private String title;
    private String accountType;
    private int accountId;
    private String requestDate;
    private String responseDate;
    private int controlCode;
    private String status;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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


    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
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
