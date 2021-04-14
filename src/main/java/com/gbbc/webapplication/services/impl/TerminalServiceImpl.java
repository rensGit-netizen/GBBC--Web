package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.beans.TerminalRequest;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.interfaces.TerminalService;
import com.gbbc.webapplication.viewmodel.PinTerminalStatus;
import com.gbbc.webapplication.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Implementing Service class for TerminalRequestController
 *
 * @author R. Portzgen
 */
@Service
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;

    @Autowired
    BankaccountRepository bankaccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    public TerminalServiceImpl(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }


    @Override
    public List<TerminalRequest> getAllRequests() {
        List<TerminalRequest> allRequests = terminalRepository.getAllByOrderByStatusAsc();
        return allRequests;
    }

    @Override
    public TerminalRequest getOneByBankAccount(int id) {
        TerminalRequest thisRequest = terminalRepository.findByBankAccountId(id);
        return thisRequest;
    }


    @Override
    public void handleRequest(PinTerminalStatus pinTerminalStatus) {
        TerminalRequest thisRequest = terminalRepository.findByBankAccountId(pinTerminalStatus.getAccountId());
        if (thisRequest != null) {
            if (thisRequest.getControlCode() == 0) {
                generateCodeForRequest(thisRequest, pinTerminalStatus);
            }
        }
    }

    @Override
    public void generateCodeForRequest(TerminalRequest thisRequest, PinTerminalStatus pinTerminalStatus) {
        int code = generateRandomCode();
        thisRequest.setControlCode(code);
        thisRequest.setStatus("Code verstuurd!");
        thisRequest.setResponseDate(getCurrentTime());
        terminalRepository.save(thisRequest);
        pinTerminalStatus.setControlCode(code);
        pinTerminalStatus.setResponseDate(thisRequest.getResponseDate());
        pinTerminalStatus.setStatus(thisRequest.getStatus());
    }

    @Override
    public int generateRandomCode() {
        int code = (int) Math.round((Math.random() * 99999) + 1);
        return code;
    }

    @Override
    public String getCurrentTime() {
        String current = "";
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        current = localDate.format(formatter);
        return current;
    }

    @Override
    public boolean checkForRequest(PinTerminalStatus terminalStatus){
        TerminalRequest checkRequest = terminalRepository.findByBankAccountId(terminalStatus.getAccountId());
        if(checkRequest == null) return false;
        else return true;
    }

    @Override
    public PinTerminalStatus generateRequest(PinTerminalStatus terminalStatus) {
        TerminalRequest terminalRequest = new TerminalRequest();
        BankAccount bankAccountForRequest = bankaccountRepository.findBankAccountById(terminalStatus.getAccountId());
        Customer customerForRequest = customerRepository.findCustomerById(bankAccountForRequest.getCustomer().get(0).getId());
        //create Request

            terminalRequest.setRequestDate(getCurrentTime());
            terminalRequest.setStatus("In afwachting");
            terminalRequest.setBankAccount(bankAccountForRequest);
            terminalRequest.setCustomer(customerForRequest);

            //save Request
            terminalRepository.save(terminalRequest);
            System.out.println("TerminalRequest saved in DB for " + terminalRequest.getCustomer().getFirst_name());


            //update terminalStatus
            terminalStatus.setAccountType(bankAccountForRequest.getType());
            terminalStatus.setIban(bankAccountForRequest.getIban());
            terminalStatus.setFirst_name(customerForRequest.getFirst_name());
            terminalStatus.setLast_name(customerForRequest.getLast_name());
            terminalStatus.setRequestDate(terminalRequest.getRequestDate());
            terminalStatus.setStatus(terminalRequest.getStatus());
            return terminalStatus;

    }

    @Override
    public List<String> getAllGeneratedCodes() {
        return terminalRepository.getAllControlCodes();
    }

    @Override
    public boolean isValidCombination(String iban, int code) {
        boolean isExistingCombination = false;
        BankAccount isValid = bankaccountRepository.findBankAccountByIbanEndsWith(iban);
        if(isValid != null){
            TerminalRequest request = terminalRepository.getTerminalRequestByBankAccountLikeAndControlCode(isValid, code);
            isExistingCombination = request != null;
        }
        return isExistingCombination;
    }

    @Override
    public boolean isValidUser(String accountNr) {
        boolean isValidRequest = false;
        try {
            BankAccount bankAccount = bankaccountRepository.findBankAccountByIbanEndsWith(accountNr);
            if(bankAccount != null) {
                TerminalRequest terminalRequest = terminalRepository.findByBankAccountId(bankAccount.getId());
                isValidRequest = true;

                isValidRequest = terminalRequest != null;
            }
        } catch (NullPointerException e) {
            System.out.println("ongeldige invoer");;
        }
        return isValidRequest;
    }
}