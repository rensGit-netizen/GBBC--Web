package com.gbbc.webapplication.API.LinkAtmTerminal;
/**
 * @author E. Koo
 */

public class LinkAtmTerminalRequest {
    private int code;
    private String iban;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
