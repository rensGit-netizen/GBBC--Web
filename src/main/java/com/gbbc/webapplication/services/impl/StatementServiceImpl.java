package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.interfaces.StatementServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementServiceImpl implements StatementServiceInterface {

    @Autowired
    public BankaccountRepository bankaccountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public StatementServiceImpl(BankaccountRepository bankaccountRepository, CustomerRepository customerRepository) {
        this.bankaccountRepository = bankaccountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<BankAccount> getCustomerAccounts(Customer customer){
        List <BankAccount> allAccountsToDisplay = new ArrayList<>();
        List<BankAccount> allCustomerAccounts = bankaccountRepository.findBankAccountsByCustomer(customer);
        allAccountsToDisplay.addAll(allCustomerAccounts);
        List<BankAccount> allAuthorizedAccounts = bankaccountRepository.findBankAccountsByGemachtigde(customer);
        allAccountsToDisplay.addAll(allAuthorizedAccounts);

        return allAccountsToDisplay;
    }

    @Override
    public String getTransactionOverview(BankAccount bankAccount, Model model) {
        String returnValue = "transactionView";
        if (bankAccount !=null){
            BankAccount getBankAccount = bankaccountRepository.findBankAccountById(bankAccount.getId());
            model.addAttribute("transactionBankAccount", getBankAccount);
        }
        else {
            returnValue = "accountStatementView";
        }
        return returnValue;
    }
}
