package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.API.transferRequest.TransferRequest;
import com.gbbc.webapplication.API.transferRequest.TransferResponse;
import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Transaction;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.TransactionRepository;
import com.gbbc.webapplication.services.interfaces.TransferRestServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class TransferRestServiceImpl implements TransferRestServiceInterface {

    @Autowired
    BankaccountRepository bankaccountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransferResponse getTransferResponse(TransferRequest transferRequest) {
        String responsValue = "Geslaagd";
        if (transferRequest == null) {
            responsValue = "Error";
        } else {
            responsValue = checkData(transferRequest, responsValue);
        }
        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setMessage(responsValue);
        return transferResponse;
    }

    @Override
    public String checkData(TransferRequest transferRequest, String responsValue) {
        BankAccount debitAccount = bankaccountRepository.findBankAccountByIbanEndsWithAndPinNumber
                ("GBBC" + transferRequest.getDebetBankAcc(), Integer.parseInt(transferRequest.getPincodeDebetAcc()));
        BankAccount creditAccount = bankaccountRepository.findBankAccountByIbanEndsWith("GBBC" + transferRequest.getCreditBankAcc());
//            System.out.println(debitAccount.getIban());
//            System.out.println(creditAccount.getIban());
        if (debitAccount == null || creditAccount == null) {
            responsValue = "Onjuiste invoer";
        } else {
            responsValue = checkIfCustomerCanPay(transferRequest, responsValue, debitAccount, creditAccount);
        }
        return responsValue;
    }

    @Override
    public String checkIfCustomerCanPay(TransferRequest transferRequest, String responsValue, BankAccount debitAccount, BankAccount creditAccount) {
        if ((debitAccount.getSaldo().doubleValue() - Double.parseDouble(transferRequest.getAmount())) < -250) {
            responsValue = "Geen saldo";
        } else {
            saveTransaction(transferRequest, debitAccount, creditAccount);
        }
        return responsValue;
    }

    @Override
    public void saveTransaction(TransferRequest transferRequest, BankAccount debitAccount, BankAccount creditAccount) {
        debitAccount.setSaldo(debitAccount.getSaldo().subtract(new BigDecimal(Double.parseDouble(transferRequest.getAmount()))));
        creditAccount.setSaldo(creditAccount.getSaldo().add(new BigDecimal(Double.parseDouble(transferRequest.getAmount()))));
        Transaction transaction = new Transaction();
        transaction.setAmount(Double.parseDouble(transferRequest.getAmount()));
        transaction.setDescription(transferRequest.getDescription());
        transaction.setDebetAccount(debitAccount);
        transaction.setCreditAccount(creditAccount);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        bankaccountRepository.save(debitAccount);
        bankaccountRepository.save(creditAccount);
        transactionRepository.save(transaction);
    }
}
