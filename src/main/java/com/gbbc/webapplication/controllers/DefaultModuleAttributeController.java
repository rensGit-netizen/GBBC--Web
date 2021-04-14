package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.*;
import com.gbbc.webapplication.viewmodel.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class DefaultModuleAttributeController {

    @ModelAttribute("customer")
    public Customer getDefaultCustomer(){return new Customer();}

    @ModelAttribute("authorizePerson")
    public Authorize getDefaultAuthorizedPerson(){
        return new Authorize();
    }

    @ModelAttribute("employee")
    public Employee getDefaultEmployee(){return new Employee();}

    @ModelAttribute("account")
    public BankAccount getDefaultBankAccount(){return new BankAccount();}

    @ModelAttribute("openAccount")
    public OpenAccount getDefaultOpenAccount(){return new OpenAccount();}

    @ModelAttribute("AddAccountHolderRequest")
    public AddAccountHolderRequest getDefaultAccountHolderReq(){return new AddAccountHolderRequest();}

    @ModelAttribute("terminalRequest")
    public TerminalRequest getDefaultTerminalRequest(){return new TerminalRequest();}

    @ModelAttribute("pinTerminalStatus")
    public PinTerminalStatus getDefaultPinTerminalStatus(){
        return new PinTerminalStatus();
    }

    @ModelAttribute("transaction")
    public Transaction getDefaultTransaction(){return new Transaction();}

    @ModelAttribute("transactionViewModel")
    public TransactionViewModel getDefaultTransactionViewModel(){return new TransactionViewModel();}

    @ModelAttribute("bankIban")
    public BankAccountIbanViewModel getDefaultBankAccountIbanViewModel()
    {return new BankAccountIbanViewModel();}
}
