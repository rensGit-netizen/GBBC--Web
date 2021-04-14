package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.services.interfaces.TransactionViewControllerServiceInterface;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class TransactionViewControllerServiceImpl implements TransactionViewControllerServiceInterface {

    @Autowired
    public BankaccountRepository bankaccountRepository;

    @Override
    public String getTransactionView(TransactionViewModel transaction, Model model) {
        if (checkTransaction(transaction)) return null;
        String returnValue = "transactionConfirmationView";
        BankAccount debitBankAccount = getDebitBankAccount(transaction);
        BankAccount creditBankAccount = getCreditBankAccount(transaction);
        if (checkIfAccountExist(model, creditBankAccount)) return null;
        if (checkTheDebt(transaction, model)) return null;
        System.out.println("the date is "+ transaction.getDate().toString());
        model.addAttribute("transactionViewMapping", transaction);
        model.addAttribute("debitBankAccountMapping",debitBankAccount);
        return returnValue;
    }

    @Override
    public BankAccount getCreditBankAccount(TransactionViewModel transaction) {
        BankAccount creditBankAccount = bankaccountRepository.findBankAccountByIban(transaction.getCreditIban());
        return creditBankAccount;
    }

    @Override
    public BankAccount getDebitBankAccount(TransactionViewModel transaction) {
        BankAccount debitBankAccount = bankaccountRepository.findBankAccountById(transaction.getBankId());
        return debitBankAccount;
    }

    @Override
    public boolean checkTheDebt(TransactionViewModel transaction, Model model) {
        if ((transaction.getTotalAmount()- transaction.getAmount())<-250){
            model.addAttribute("sellSoul", "Even if you sell your soul, it wont cover your debt.");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIfAccountExist(Model model, BankAccount creditBankAccount) {
        if (creditBankAccount ==null){
            model.addAttribute("doesntExist", "Verkeerd rekening nummer. Probeer opnieuw.");
            return true;
        }
        return false;
    }

    @Override
    public boolean checkTransaction(TransactionViewModel transaction) {
        if(transaction==null){
            return true;
        }
        if (transaction.getCreditIban().isEmpty() || transaction.getCreditIban().isBlank()){
            return true;
        }
        return false;
    }
}
