package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import org.springframework.ui.Model;

import java.util.List;

public interface StatementServiceInterface {
    List<BankAccount> getCustomerAccounts(Customer customer);

    String getTransactionOverview(BankAccount bankAccount, Model model);
}
