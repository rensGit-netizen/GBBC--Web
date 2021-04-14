package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface OverviewViewServiceInterface {
    void getTransactionValue(BankAccount bankaccount, Model model);

    String getOverviewView(BankAccount bankaccount, Model model, HttpSession session);

    BankAccount getBankAccount(String iban);
}
