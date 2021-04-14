package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.viewmodel.Authorize;

import java.util.List;

public interface AuthorizePersonService {

    // give a person authorization
    public void authorizePerson(Authorize authorize);

    // check if authorized person is a customer
    public boolean isAlreadyCustomer(String username);

    /**
     * @should add authorization to existing Customer
     * @should add authorized Customer to BankAccount
     * @param authorize attributes from filled form
     * @param bankAccount to add authorization
     * @param toAuthorizeCustomer authorize this customer
     */
    public void addAuthorizedCustomerToBankAccount(Authorize authorize, BankAccount bankAccount, Customer toAuthorizeCustomer);

    // if authorized person isn't a customer, create authorized person account
    public void createAuthorizedPersonAccount(Authorize authorize);

    public List<String> getAllCustomers();
}
