package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.AccountRequest;
import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.services.interfaces.AccountHolderService;
import com.gbbc.webapplication.viewmodel.AddAccountHolderRequest;
import com.gbbc.webapplication.repository.AccountLinkRepository;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Service class to handle all requests to accountLink Repository
 * Workerclass for LinkAccountHolderController
 *
 * @author R. Portzgen
 */
@Service
public class AccountHolderServiceImpl implements AccountHolderService {

    @Autowired
    private AccountLinkRepository accountLinkRepository;
    @Autowired
    private BankaccountRepository bankaccountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String processRequest(AddAccountHolderRequest request, Model model, Customer customer, HttpSession session) {
        AccountRequest accountRequest = accountLinkRepository.findByBankAccount_Iban(request.getIban());
        String returnValue = "accountOverviewView";
        if (accountRequest != null && accountRequest.getCode() == request.getCode()) {
            customer = handleRequest(request, customer);
            accountLinkRepository.delete(accountRequest);
            session.setAttribute("userSession", customer);
            model.addAttribute("infopanel", "U heeft succesvol een rekening gekoppeld, bekijk je rekening nu!");
        } else{
            model.addAttribute("invalidCode", "Controleer je gegevens of neem contact op met de rekeninghouder.");
            model.addAttribute("AddAccountHolderRequest", new AddAccountHolderRequest());
            returnValue = "linkAccountView";
        }
        return returnValue;
    }

    @Override
    public Customer handleRequest(AddAccountHolderRequest request, Customer customer) {
        //Get the bankaccount
        BankAccount thisAccount = bankaccountRepository.findBankAccountByIban(request.getIban());
        //Get the customer
        customer = customerRepository.searchCustomerById(customer.getId());
        //Add user to userlist
        List<Customer> allCustomersForAccount = thisAccount.getCustomer();
        allCustomersForAccount.add(customer);
        thisAccount.setCustomer(allCustomersForAccount);

        //Add bankaccount to bankaccountList
        List<BankAccount> allAccountsForCustomer = customer.getBankAccount();
        allAccountsForCustomer.add(thisAccount);
        customer.setBankAccount(allAccountsForCustomer);
        //Save
        customerRepository.save(customer);
        bankaccountRepository.save(thisAccount);
        return customer;
    }

    @Override
    public String createAccountRequest(AddAccountHolderRequest request, Model model, String returnValue, Customer loggedInCustomer, HttpSession session) {
        BankAccount checkAccount = bankaccountRepository.findBankAccountByIban(request.getIban());
        Customer newAccountHolder = customerRepository.searchByUserName(request.getCustomer_username());
        AccountRequest thisRequest = accountLinkRepository.findByBankAccount_Iban(request.getIban());
        if (!isAccountHolder(checkAccount, loggedInCustomer) || newAccountHolder == null || thisRequest != null) {
            model.addAttribute("invalidCode", "Controleer je gegevens of neem contact op met de rekeninghouder");
            model.addAttribute("AddAccountHolderRequest", new AddAccountHolderRequest());
            returnValue = "addAccountHolder";
        } else {
            createAndSaveRequest(request, loggedInCustomer, checkAccount);
        }
        session.setAttribute("userSession", loggedInCustomer);
        session.setAttribute("account", checkAccount);
        model.addAttribute("infopanel", "U heeft succesvol een rekeninghouder toegevoegd, geef de code aan deze persoon zodat deze toegang krijgt tot het account");
        return returnValue;
    }

    @Override
    public void createAndSaveRequest(AddAccountHolderRequest request, Customer loggedInCustomer, BankAccount checkAccount) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setBankAccount(checkAccount);
        accountRequest.setCustomer(loggedInCustomer);
        accountRequest.setCode(request.getCode());
        accountLinkRepository.save(accountRequest);
        System.out.println(accountRequest.getBankAccount().toString() + accountRequest.getCode());
        System.out.println("account saved");
    }

    @Override
    public boolean isAccountHolder(BankAccount checkAccount, Customer customer) {
        List<Customer> allAccountHolders = checkAccount.getCustomer();
        boolean check = false;
        for (Customer c : allAccountHolders) {
            if (c.getId() == customer.getId()) {
                check = true;
            }
        }
        return check;
    }
}
