package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Transaction;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.TransactionRepository;
import com.gbbc.webapplication.services.interfaces.OverviewViewServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class OverviewViewServiceImpl implements OverviewViewServiceInterface {

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public BankaccountRepository bankaccountRepository;

    @Override
    public void getTransactionValue(BankAccount bankaccount, Model model) {
        List<Transaction> transactionList = transactionRepository.findTransactionByDebetAccount_IdOrCreditAccount_IdOrderByDateDesc(bankaccount.getId(),bankaccount.getId());
        BankAccount bankAccountforBalance = bankaccountRepository.findBankAccountById(bankaccount.getId());
        model.addAttribute("transactionList", transactionList);

        //  System.out.println("transaction description "+ transactionList.get(0).getDescription());
        model.addAttribute("accountForBalance", bankAccountforBalance);
        System.out.println(bankAccountforBalance.getSaldo());
    }

    @Override
    public String getOverviewView(BankAccount bankaccount, Model model, HttpSession session) {
        BankAccount checkBankAccount = getBankAccount(bankaccount.getIban());
        String returnValue = "accountStatementView";
        if (checkBankAccount != null) {
            getTransactionValue(checkBankAccount, model);
            session.setAttribute("selectedIban", checkBankAccount.getIban());
            System.out.println(" iban: " + checkBankAccount.getIban());
        } else {
            returnValue = "accountOverviewView";
        }
        return returnValue;
    }

    @Override
    public BankAccount getBankAccount(String iban){
        BankAccount bankAccount = bankaccountRepository.findBankAccountByIban(iban);
        return bankAccount;
    }
}
