package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.viewmodel.Business;
import com.gbbc.webapplication.viewmodel.Sector;

import java.util.List;

/**
 * Interface for Service class to handle requests to Bankaccount Repository
 * Interface for Service class to handle requests to Customer Repository
 *
 * @author R. Portzgen
 */
public interface BusinessService {

    List<Business> getPrivateBankAccounts();

    /**
     * Method to get all bankAccount objects from DB
     * @should get an Arraylist of all business Accounts from DB
     * @should store all bankAccount data in Arraylist of Business Object
     * @return Array list of Business Objects
     */
    List<Business> getBankAccounts();

    /**
     * Method to the 10 business bankaccounts with the highest balance from DB
     * @should get an Arraylist of business Accounts from DB
     * @should store bankAccount data in Business Object
     * @should store the Business Objects in an Arraylist
     * @return Array list of Business Objects
     */
    List<Business> getQuoteTen();

    /**
     * Method to the 10 business bankaccounts with the highest balance from DB
     * @should get an Arraylist of business Accounts from DB
     * @should store bankAccount data in Business Object
     * @should store the Business Objects in an Arraylist
     * @return Array list of Business Objects
     */
    List<Business> getMostActiveAccounts();

    /**
     * Method to all the sectors and their average balance from the DB
     * @should get an Arraylist of segmentnames  from DB
     * @should get an Arraylist of segment averages  from DB
     * @should store the data in Sector Object
     * @should store the Sector Objects in an Arraylist
     * @return Array list of Sector Objects
     */
    List<Sector> getAllSectorsWithAvg();

    String getCompanyNameByAccountNumber(String accountnumber);
}
