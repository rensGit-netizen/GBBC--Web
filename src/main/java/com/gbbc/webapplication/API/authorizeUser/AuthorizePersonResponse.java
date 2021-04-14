package com.gbbc.webapplication.API.authorizeUser;
/**
 * @author pair programming Rens & Emily
 */

public class AuthorizePersonResponse {
    private String message;

    public AuthorizePersonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
