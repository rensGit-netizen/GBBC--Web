package com.gbbc.webapplication.API.LinkAtmTerminal;
/**
 * @author E. Koo
 */

public class LinkAtmTerminalResponse {
    private String message;

    public LinkAtmTerminalResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
