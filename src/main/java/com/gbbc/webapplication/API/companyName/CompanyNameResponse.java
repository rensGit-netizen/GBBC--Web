package com.gbbc.webapplication.API.companyName;

public class CompanyNameResponse {
    private String message;

    public CompanyNameResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
