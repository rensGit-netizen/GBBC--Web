package com.gbbc.webapplication.API.transferRequest;

public class TransferRequest {

    private String creditBankAcc;
    private String debetBankAcc;
    private String amount;
    private String description;
    private String pincodeDebetAcc;

    public String getCreditBankAcc() {
        return creditBankAcc;
    }

    public void setCreditBankAcc(String creditBankAcc) {
        this.creditBankAcc = creditBankAcc;
    }

    public String getDebetBankAcc() {
        return debetBankAcc;
    }

    public void setDebetBankAcc(String debetBankAcc) {
        this.debetBankAcc = debetBankAcc;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPincodeDebetAcc() {
        return pincodeDebetAcc;
    }

    public void setPincodeDebetAcc(String pincodeDebetAcc) {
        this.pincodeDebetAcc = pincodeDebetAcc;
    }
}
