package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.viewmodel.AddAccountHolderRequest;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

/**
 * Interface for Service class to handle requests to Bankaccount Repository
 * Interface for Service class to handle requests to Customer Repository
 *
 * @author R. Portzgen
 */
public interface AccountHolderService {

    String processRequest(AddAccountHolderRequest request, Model model, Customer customer, HttpSession session);

    /**
     * Method to add the bankaccount to the customer and vice versa
     * @should add the given bankaccount to the given customer
     * @param request
     * @param customer
     * @return
     */
    Customer handleRequest(AddAccountHolderRequest request, Customer customer);

    String createAccountRequest(AddAccountHolderRequest request, Model model, String returnValue, Customer loggedInCustomer, HttpSession session);


    void createAndSaveRequest(AddAccountHolderRequest request, Customer loggedInCustomer, BankAccount checkAccount);

    /**
     * @should return false when Customer is not an Accountholder of given BankAccount
     * @should return true when Customer is an Accountholder of given BankAccount
     * @param checkAccount
     * @param customer
     * @return
     */
    boolean isAccountHolder(BankAccount checkAccount, Customer customer);
}
