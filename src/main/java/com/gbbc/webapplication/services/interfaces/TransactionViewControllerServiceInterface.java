package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.springframework.ui.Model;

public interface TransactionViewControllerServiceInterface {
    String getTransactionView(TransactionViewModel transaction, Model model);

    /**
     * @should return BankAccount object from database if found with matching IBAN.
     * @should return null if bankaccount isn't found with matching IBAN
     * @param transaction
     * @return
     */
    BankAccount getCreditBankAccount(TransactionViewModel transaction);

    /**
     * @should return BankAccount object from database if found with matching Id.
     * @should return null if bankaccount isn't found with matching Id
     * @param transaction
     * @return
     */
    BankAccount getDebitBankAccount(TransactionViewModel transaction);

    /**
     * @should return true if currentaccount minus transaction is samller than -250.
     * @should return false if currentaccount minus transaction is -250 or higher.
     * @param transaction
     * @param model
     * @return
     */
    boolean checkTheDebt(TransactionViewModel transaction, Model model);

    /**
     * @should return true if creditBankAccount is null.
     * @should return false if creditBankAccount is not null.
     * @param model
     * @param creditBankAccount
     * @return
     */
    boolean checkIfAccountExist(Model model, BankAccount creditBankAccount);

    /**
     * @should return true if the creditIban in transaction is empty or null
     * @should return false if the creditIban in transaction is not empty and not null
     * @param transaction
     * @return
     */
    boolean checkTransaction(TransactionViewModel transaction);
}
