package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Transaction;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.springframework.ui.Model;

public interface TransactionConfirmationServiceInterface {
    String getTransactionInformation(TransactionViewModel transaction, Model model);

    /**
     * @should return a TransactionObject with the information
     * @param transaction
     * @param debitBankAccount
     * @param creditBankAccount
     * @return
     */
    Transaction getTransactionInfo(TransactionViewModel transaction, BankAccount debitBankAccount, BankAccount creditBankAccount);

    void newCreditSaldo(TransactionViewModel transaction, BankAccount creditBankAccount);

    void newDebitSaldo(TransactionViewModel transaction, BankAccount debitBankAccount);
}
