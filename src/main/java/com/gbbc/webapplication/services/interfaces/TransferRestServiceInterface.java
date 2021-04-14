package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.API.transferRequest.TransferRequest;
import com.gbbc.webapplication.API.transferRequest.TransferResponse;
import com.gbbc.webapplication.beans.BankAccount;

public interface TransferRestServiceInterface {
    TransferResponse getTransferResponse(TransferRequest transferRequest);

    String checkData(TransferRequest transferRequest, String responsValue);

    String checkIfCustomerCanPay(TransferRequest transferRequest, String responsValue, BankAccount debitAccount, BankAccount creditAccount);

    void saveTransaction(TransferRequest transferRequest, BankAccount debitAccount, BankAccount creditAccount);
}
