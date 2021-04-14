package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Transaction;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.TransactionRepository;
import com.gbbc.webapplication.services.interfaces.TransactionConfirmationServiceInterface;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;

@Service
public class TransactionConfirmationServiceImpl implements TransactionConfirmationServiceInterface {

    @Autowired
    public TransactionRepository transactionRepository;

    @Autowired
    public BankaccountRepository bankaccountRepository;

    @Override
    public String getTransactionInformation(TransactionViewModel transaction, Model model) {
        String returnValue = "transactionSucceededView";
        BankAccount debitBankAccount = bankaccountRepository.findBankAccountByIban(transaction.getDebitIban());
        newDebitSaldo(transaction, debitBankAccount);
        BankAccount creditBankAccount = bankaccountRepository.findBankAccountByIban(transaction.getCreditIban());
        newCreditSaldo(transaction, creditBankAccount);
        Transaction newTransaction = getTransactionInfo(transaction, debitBankAccount, creditBankAccount);
        System.out.println("in transactionConfirmationController");
        System.out.println("transaction description is "+ transaction.getDescription());
        bankaccountRepository.save(debitBankAccount);
        bankaccountRepository.save(creditBankAccount);
        transactionRepository.save(newTransaction);
        model.addAttribute("bankIban", debitBankAccount.getIban());
        return returnValue;
    }

    /**
     * @should return filled or partially filled Transaction Object
     * @should be awesome
     * @param transaction
     * @param debitBankAccount
     * @param creditBankAccount
     * @return
     */
    @Override
    public Transaction getTransactionInfo(TransactionViewModel transaction, BankAccount debitBankAccount, BankAccount creditBankAccount) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setDescription(transaction.getDescription());
        newTransaction.setDebetAccount(debitBankAccount);
        newTransaction.setCreditAccount(creditBankAccount);
        newTransaction.setDate(transaction.getDate());
        return newTransaction;
    }

    @Override
    public void newCreditSaldo(TransactionViewModel transaction, BankAccount creditBankAccount) {
        creditBankAccount.setSaldo(creditBankAccount.getSaldo().add(new BigDecimal(transaction.getAmount())));
    }

    @Override
    public void newDebitSaldo(TransactionViewModel transaction, BankAccount debitBankAccount) {
        debitBankAccount.setSaldo(debitBankAccount.getSaldo().subtract(new BigDecimal(transaction.getAmount())));
    }
}
