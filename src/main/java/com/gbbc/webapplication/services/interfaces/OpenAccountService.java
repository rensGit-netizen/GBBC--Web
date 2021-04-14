package com.gbbc.webapplication.services.interfaces;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.viewmodel.OpenAccount;

import java.util.Random;
/**
 * @author Emily
 */
public interface OpenAccountService {

    /**
     * @should be true if customer already exists
     * @should be false if username is not in database
     * @param username filled in field in open account
     * @return true if customer exists in database
     */
    boolean isAlreadyCustomer(String username);

    /**
     * @should add bankAccount to existing Customer
     * @param openExtraAccount if is already customer
     */
    void addExtraAccount(OpenAccount openExtraAccount, Customer owner);

    /**
     * @should create a new customer if customer
     * @should create a new bank account
     * @param storeAccount new account
     */
    void createAccount(OpenAccount storeAccount);

    /**
     * @should throw exception if customer is null
     * @param storeAccount field values
     * @return set customer field values
     */
    Customer setCustomer(OpenAccount storeAccount);

    /**
     * @should generate an unique iban
     * @return iban
     */
    String generateIban();

    void generateStringDigits(Random random, StringBuilder numberString, int amount);
}
