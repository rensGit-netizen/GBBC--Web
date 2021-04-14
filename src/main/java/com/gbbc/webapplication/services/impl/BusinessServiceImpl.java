package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.services.interfaces.BusinessService;
import com.gbbc.webapplication.viewmodel.Business;
import com.gbbc.webapplication.viewmodel.Sector;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing Service class to handle requests to Bankaccount Repository
 * Implementing Service class to handle requests to Customer Repository
 * Workerclass for BusinessController
 *
 * @author R. Portzgen
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BankaccountRepository bankaccountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public BusinessServiceImpl(BankaccountRepository bankaccountRepository, CustomerRepository customerRepository) {
        this.bankaccountRepository = bankaccountRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Business> getPrivateBankAccounts(){
        List<Business> allPrivateAccountInfo = new ArrayList<>();
        List<BankAccount> allPrivateAccounts = bankaccountRepository.getAllPrivateAccounts();
        return getBusinesses(allPrivateAccountInfo, allPrivateAccounts);
    }

    @Override
    public List<Business> getBankAccounts() {
        List<Business> allBusinessSmes = new ArrayList<>();
        List<BankAccount> allBusinessAccounts = bankaccountRepository.getAllBusinessAccounts();
        return getBusinesses(allBusinessSmes, allBusinessAccounts);
    }

    @Override
    public List<Business> getQuoteTen() {
        List<Business> quoteTenSmes = new ArrayList<>();
        List<BankAccount> quoteTenBusinessAccountIDS = bankaccountRepository.findTop10BySectorIsNotOrderBySaldoDesc("prive");
        return getBusinesses(quoteTenSmes, quoteTenBusinessAccountIDS);
    }

    private List<Business> getBusinesses(List<Business> quoteTenSmes, List<BankAccount> quoteTenBusinessAccountIDS) {
        if (quoteTenBusinessAccountIDS != null) {
            for (BankAccount b : quoteTenBusinessAccountIDS) {
                Business business = new Business();
                business.setId(b.getId());
                business.setCustomer_id(b.getCustomer().get(0).getId());
                business.setTitle(b.getTitle());
                business.setIban(b.getIban());
                business.setSaldo(b.getSaldo());
                Customer businessCustomer = customerRepository.searchCustomerById(b.getCustomer().get(0).getId());
                business.setUser_name(businessCustomer.getUserName());
                business.setFirst_name(businessCustomer.getFirst_name());
                business.setLast_name(businessCustomer.getLast_name());
                business.setSector(b.getSector());
                quoteTenSmes.add(business);
            }
        }
        return quoteTenSmes;
    }

    @Override
    public List<Business> getMostActiveAccounts() {
        List<String> mostActiveAccountIDs = bankaccountRepository.getNew10MostActiveBankAccountIban();
        List<Business> tenMostActiveBusinessAccounts = new ArrayList<>();
        for (String i : mostActiveAccountIDs) {
            BankAccount bankAccount = bankaccountRepository.findBankAccountByIban(i);
            Customer customer = customerRepository.findCustomerById(bankAccount.getCustomer().get(0).getId());
            Business business = new Business();
            business.setTitle(bankAccount.getTitle());
            business.setIban(bankAccount.getIban());
            business.setSaldo(bankAccount.getSaldo());
            business.setUser_name(customer.getUserName());
            tenMostActiveBusinessAccounts.add(business);
        }
        return tenMostActiveBusinessAccounts;
    }

    @Override
    public List<Sector> getAllSectorsWithAvg() {
        List<String> sectorNames = bankaccountRepository.getAllSegmentNames();
        List<String> avgBalance = bankaccountRepository.getAllSegmentAvgs();
        List<Sector> sectorList = new ArrayList<>();
        int index = 0;
        for (String s : sectorNames) {
            Sector sector = new Sector();
            sector.setName(s);
            sector.setAvgBalance(avgBalance.get(index));
            sectorList.add(sector);
            index++;
        }
        return sectorList;
    }

    @Override
    public String getCompanyNameByAccountNumber(String accountnumber){
        BankAccount thisAccount = bankaccountRepository.findBankAccountByIbanEndsWith(accountnumber);
        String companyName = thisAccount.getTitle();
        return companyName;
    }

}
