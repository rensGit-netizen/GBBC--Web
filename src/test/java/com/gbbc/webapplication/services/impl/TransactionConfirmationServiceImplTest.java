package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Transaction;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.TransactionRepository;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.mockito.Mockito.*;

class TransactionConfirmationServiceImplTest {
    @Mock
    TransactionRepository transactionRepository;
    @Mock
    BankaccountRepository bankaccountRepository;
    @InjectMocks
    TransactionConfirmationServiceImpl transactionConfirmationServiceImpl;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetTransactionInformation() {
        BankAccount bankAccountDebit = new BankAccount();
        bankAccountDebit.setIban("123");
        bankAccountDebit.setSaldo(new BigDecimal(500));
        BankAccount bankAccountCredit = new BankAccount();
        bankAccountCredit.setIban("122");
        bankAccountCredit.setSaldo(new BigDecimal(500));
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setCreditIban("122");
        transactionViewModel.setDebitIban("123");
        transactionViewModel.setAmount(100);
        when(bankaccountRepository.findBankAccountByIban(transactionViewModel.getCreditIban())).thenReturn(bankAccountCredit);
        when(bankaccountRepository.findBankAccountByIban(transactionViewModel.getDebitIban())).thenReturn(bankAccountDebit);
        String result = transactionConfirmationServiceImpl.getTransactionInformation(transactionViewModel, model);
        Assertions.assertEquals("transactionSucceededView", result);
    }

    /**
     * returns a Transaction object containing the exact information given to that object bij TransactionViewModel
     * and both BankAccount objects
     */
    @Test
    void testGetTransactionInfo() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setAmount(100);
        transactionViewModel.setDescription("Hello");
        transactionViewModel.setDate(new Timestamp(2000, 2, 4, 22, 32, 44, 99));
        BankAccount bankAccountDebit = new BankAccount();
        bankAccountDebit.setId(1);
        BankAccount bankAccountCredit = new BankAccount();
        bankAccountCredit.setId(2);
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(transactionViewModel.getAmount());
        expectedTransaction.setDescription(transactionViewModel.getDescription());
        expectedTransaction.setDebetAccount(bankAccountDebit);
        expectedTransaction.setCreditAccount(bankAccountCredit);
        expectedTransaction.setDate(transactionViewModel.getDate());
        Transaction result = transactionConfirmationServiceImpl.getTransactionInfo(transactionViewModel, bankAccountDebit, bankAccountCredit);
        Assertions.assertTrue(new ReflectionEquals(expectedTransaction, null).matches(result));
    }

    /**
     * returns a Transaction object containing the wrong information given to that object bij TransactionViewModel
     * and both BankAccount objects
     */
    @Test
    void testGetTransactionInfoUnHappy() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setAmount(100);
        transactionViewModel.setDescription("Hello");
        transactionViewModel.setDate(new Timestamp(2000, 2, 4, 22, 32, 44, 99));
        BankAccount bankAccountDebit = new BankAccount();
        bankAccountDebit.setId(1);
        BankAccount bankAccountCredit = new BankAccount();
        bankAccountCredit.setId(2);
        Transaction expectedTransaction = new Transaction();
        expectedTransaction.setAmount(102);
        expectedTransaction.setDescription(transactionViewModel.getDescription());
        expectedTransaction.setDebetAccount(bankAccountDebit);
        expectedTransaction.setCreditAccount(bankAccountCredit);
        expectedTransaction.setDate(transactionViewModel.getDate());
        Transaction result = transactionConfirmationServiceImpl.getTransactionInfo(transactionViewModel, bankAccountDebit, bankAccountCredit);
        Assertions.assertFalse(new ReflectionEquals(expectedTransaction, null).matches(result));
    }

    /**
     * @should add the amount in the transactionViewModel to the Amount on the bankAccount
     */
    @Test
    void testNewCreditSaldo() {

        TransactionViewModel transaction = new TransactionViewModel();
        transaction.setAmount(200);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setSaldo(new BigDecimal(100));
        double expectedSaldo = (bankAccount.getSaldo().doubleValue())+(transaction.getAmount());
        transactionConfirmationServiceImpl.newCreditSaldo(transaction, bankAccount);
        double saldo = bankAccount.getSaldo().doubleValue();
        Assertions.assertEquals(expectedSaldo, saldo);
    }

    /**
     * @should subtract the amount in the transactionViewModel to the Amount on the bankAccount
     */
    @Test
    void testNewDebitSaldo() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setAmount(100);
        BankAccount debitBankAccount = new BankAccount();
        debitBankAccount.setSaldo(new BigDecimal(500));
        double expectedSaldo = (debitBankAccount.getSaldo().doubleValue()) - (transactionViewModel.getAmount());//subtraction
        transactionConfirmationServiceImpl.newDebitSaldo(transactionViewModel, debitBankAccount);
        double newSaldo = debitBankAccount.getSaldo().doubleValue();
        Assertions.assertEquals(expectedSaldo, newSaldo);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme