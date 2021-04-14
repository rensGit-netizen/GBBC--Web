package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

class TransactionViewControllerServiceImplTest {
    @Mock
    BankaccountRepository bankaccountRepository;
    @Mock
    Model model;
    @InjectMocks
    TransactionViewControllerServiceImpl transactionViewControllerServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * @returns the string"transactionConfirmationView" if the Transactionviewmodel contains information and
     * a BankAccount can be found for debit and credit.
     */
    @Test
    void testGetTransactionView() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setBankId(3);
        transactionViewModel.setCreditIban("123");
        BankAccount bankAccountDebit = new BankAccount();
        bankAccountDebit.setId(3);
        BankAccount bankAccountCredit = new BankAccount();
        bankAccountDebit.setIban("123");
        when(bankaccountRepository.findBankAccountById(transactionViewModel.getBankId())).thenReturn(bankAccountDebit);
        when(bankaccountRepository.findBankAccountByIban(transactionViewModel.getCreditIban())).thenReturn(bankAccountCredit);

        String result = transactionViewControllerServiceImpl.getTransactionView(transactionViewModel, model);
        Assertions.assertEquals("transactionConfirmationView", result);
    }

    /**
     * @returns null if the Transactionviewmodel contains no information
     */
    @Test
    void testGetTransactionViewUnhappy() {
        TransactionViewModel transactionViewModel = null;
        String result = transactionViewControllerServiceImpl.getTransactionView(transactionViewModel, model);
        Assertions.assertNull(result);
    }

    /**
     * @returns null if the Transactionviewmodel contains information and
     * a BankAccount can not be found for debit and credit.
     */
    @Test
    void testGetTransactionViewUnHappy2() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setBankId(3);
        transactionViewModel.setCreditIban("123");
        BankAccount bankAccountDebit = new BankAccount();
        bankAccountDebit.setId(4);
        BankAccount bankAccountCredit = new BankAccount();
        bankAccountDebit.setIban("124");
        when(bankaccountRepository.findBankAccountById(transactionViewModel.getBankId())).thenReturn(null);
        when(bankaccountRepository.findBankAccountByIban(transactionViewModel.getCreditIban())).thenReturn(null);

        String result = transactionViewControllerServiceImpl.getTransactionView(transactionViewModel, model);
        Assertions.assertNull(result);
    }

    /**
     * @returns BankAccount if one can be found in the database bij Iban
     */
    @Test
    void testGetCreditBankAccountHappyFlow() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban("123");
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setCreditIban("123");
        when(bankaccountRepository.findBankAccountByIban(transactionViewModel.getCreditIban())).thenReturn(bankAccount);
        BankAccount result = transactionViewControllerServiceImpl.getCreditBankAccount(transactionViewModel);
        Assertions.assertEquals(bankAccount, result);
    }

    /**
     * @returns BankAccount=null if one can not be found in the database bij Iban
     */
    @Test
    void testGetCreditBankAccountUnHappyFlow() {
        BankAccount bankAccount = null;
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setCreditIban("123");
        when(bankaccountRepository.findBankAccountByIban(transactionViewModel.getCreditIban())).thenReturn(bankAccount);

        BankAccount result = transactionViewControllerServiceImpl.getCreditBankAccount(transactionViewModel);
        Assertions.assertNull(result);
    }

    /**
     * @return bankaccount if exists
     */
    @Test
    void testGetDebitBankAccountHappyFlow() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(5);
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setBankId(5);
        when(bankaccountRepository.findBankAccountById(transactionViewModel.getBankId())).thenReturn(bankAccount);

        BankAccount result = transactionViewControllerServiceImpl.getDebitBankAccount(transactionViewModel);
        Assertions.assertEquals(bankAccount, result);
    }

    /**
     * @return null if bankaccount can't be found in database bij id.
     */
    @Test
    void testGetDebitBankAccountUnhappyFlow() {
        BankAccount bankAccount = null;
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setBankId(5);
        when(bankaccountRepository.findBankAccountById(transactionViewModel.getBankId())).thenReturn(bankAccount);

        BankAccount result = transactionViewControllerServiceImpl.getDebitBankAccount(transactionViewModel);
        Assertions.assertNull(result);
    }

    /**
     * @returns true if the total amount on bankaccount goes below -250
     */
    @Test
    void testCheckTheDebtHappyFlow() {
        Model modeltwo = new ExtendedModelMap();
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setTotalAmount(-100);
        transactionViewModel.setAmount(200);
        boolean result = transactionViewControllerServiceImpl.checkTheDebt(transactionViewModel, modeltwo);
        Assertions.assertTrue(result);
        Assertions.assertEquals("Even if you sell your soul, it wont cover your debt.", (String) modeltwo.getAttribute("sellSoul"));
    }

    /**
     * @returns true if the total amount on bankaccount goes below -250
     */
    @Test
    void testCheckTheDebtHappyFlow2() {
        Model modeltwo = new ExtendedModelMap();
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setTotalAmount(-100);
        transactionViewModel.setAmount(151);
        boolean result = transactionViewControllerServiceImpl.checkTheDebt(transactionViewModel, modeltwo);
        Assertions.assertTrue(result);
        Assertions.assertEquals("Even if you sell your soul, it wont cover your debt.", (String) modeltwo.getAttribute("sellSoul"));
    }

    /**
     * @returns false if the total amount on bankaccount is -250 or higher
     */
    @Test
    void testCheckTheDebtUnHappyFlow() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setTotalAmount(-100);
        transactionViewModel.setAmount(150);
        boolean result = transactionViewControllerServiceImpl.checkTheDebt(transactionViewModel, model);
        Assertions.assertFalse(result);
    }

    /**
     * @returns false if the total amount on bankaccount is -250 or higher
     */
    @Test
    void testCheckTheDebtUnHappyFlow2() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setTotalAmount(100);
        transactionViewModel.setAmount(150);
        boolean result = transactionViewControllerServiceImpl.checkTheDebt(transactionViewModel, model);
        Assertions.assertFalse(result);
    }


    /**
     * @returns true if BankAccount==null.
     */
    @Test
    void testCheckIfAccountExist() {
        Model modelTwo = new ExtendedModelMap();
        BankAccount bankAccount = null;
        boolean result = transactionViewControllerServiceImpl.checkIfAccountExist(modelTwo, bankAccount);
        Assertions.assertTrue(result);
        Assertions.assertEquals("Verkeerd rekening nummer. Probeer opnieuw.", (String)modelTwo.getAttribute("doesntExist"));
    }

    /**
     * @returns false if BankAccount!=null.
     */
    @Test
    void testCheckIfAccountExistUnHappy() {
        BankAccount bankAccount = new BankAccount();
        boolean result = transactionViewControllerServiceImpl.checkIfAccountExist(model, bankAccount);
        Assertions.assertFalse(result);
    }


    /**
     * @returns true if TransactionViewModel is null
     */
    @Test
    void testCheckTransaction() {
        TransactionViewModel transactionViewModel = null;
        boolean result = transactionViewControllerServiceImpl.checkTransaction(transactionViewModel);
        Assertions.assertEquals(true, result);
    }

    /**
     * @returns true if TransactionViewModel has empty credit iban
     */
    @Test
    void testCheckTransactionHappy2() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setCreditIban("");
        boolean result = transactionViewControllerServiceImpl.checkTransaction(transactionViewModel);
        Assertions.assertTrue(result);
    }

    /**
     * @returns false if TransactionViewModel has credit iban
     */
    @Test
    void testCheckTransactionUnHappy() {
        TransactionViewModel transactionViewModel = new TransactionViewModel();
        transactionViewModel.setCreditIban("hello");
        boolean result = transactionViewControllerServiceImpl.checkTransaction(transactionViewModel);
        Assertions.assertFalse(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme