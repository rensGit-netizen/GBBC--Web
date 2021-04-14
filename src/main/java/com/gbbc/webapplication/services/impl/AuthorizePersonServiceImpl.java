package com.gbbc.webapplication.services.impl;
import com.gbbc.webapplication.beans.AuthorizedPerson;
import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.AuthorizePersonRepository;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.interfaces.AuthorizePersonService;
import com.gbbc.webapplication.viewmodel.Authorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @author E. Koo
 */

@Service
public class AuthorizePersonServiceImpl implements AuthorizePersonService {

    @Autowired
    private final AuthorizePersonRepository authorizePersonRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final BankaccountRepository bankaccountRepository;

    public AuthorizePersonServiceImpl(AuthorizePersonRepository authorizePersonRepository, CustomerRepository customerRepository, BankaccountRepository bankaccountRepository) {
        this.authorizePersonRepository = authorizePersonRepository;
        this.customerRepository = customerRepository;
        this.bankaccountRepository = bankaccountRepository;
    }

    @Override
    public void authorizePerson(Authorize authorizePerson) {
        // check if authorized person is a customer
        Customer authorizeCustomer = customerRepository.findCustomerByUserName(authorizePerson.getUser_name());
        // get bankAccount for authorization
        BankAccount addToBankAccount = bankaccountRepository.findBankAccountByIban(authorizePerson.getIban());

        //TODO: if person has a customer id add authorized bankaccount to customer id
        if (isAlreadyCustomer(authorizePerson.getUser_name())) {
            // add customer to BankAccount
            List<BankAccount> addAuthorizedPerson = new ArrayList<>();
            addAuthorizedPerson.add(addToBankAccount);
            authorizeCustomer.setBankAccount(addAuthorizedPerson);
//            addToBankAccount.setAuthorizedPerson(authorizeCustomer);
            //save
            customerRepository.save(authorizeCustomer);
            bankaccountRepository.save(addToBankAccount);
        }

        if (authorizeCustomer == null) {
            // should create a authorized person
            // if person is not a customer create authorized person account
            AuthorizedPerson personToAuthorize = new AuthorizedPerson();
            // set attributes
            personToAuthorize.setFirst_name(authorizePerson.getFirst_name());
            personToAuthorize.setLast_name(authorizePerson.getLast_name());
            personToAuthorize.setUser_name(authorizePerson.getUser_name());
            personToAuthorize.setPassword(authorizePerson.getPassword());
//            authorizePerson.getIban() = personToAuthorize.setBankAccount();
            // add BankAccount to authorizePerson

            List<BankAccount> addAuthorizedPerson = new ArrayList<>();
            personToAuthorize.setBankAccount(addAuthorizedPerson);
            addAuthorizedPerson.add(addToBankAccount);

            addToBankAccount.setAuthorizedPerson(personToAuthorize);

            System.out.println(addToBankAccount.getIban());
            // save
            authorizePersonRepository.save(personToAuthorize);
            bankaccountRepository.save(addToBankAccount);
        }
    }

    @Override
    public boolean isAlreadyCustomer(String username) {
        boolean isExistingCustomer;
        Customer isValidCustomer = customerRepository.findCustomerByUserName(username);
        isExistingCustomer = isValidCustomer != null;
        return isExistingCustomer;
    }

    @Override
    public void addAuthorizedCustomerToBankAccount(Authorize authorize, BankAccount bankAccount, Customer toAuthorizeCustomer) {
        if(isAlreadyCustomer(toAuthorizeCustomer.getUserName())){

        }
    }

    @Override
    public void createAuthorizedPersonAccount(Authorize authorize) {
        //TODO: if AuthorizedPerson is not a Customer create a AuthorizedPerson Account
    }

    @Override
    public List<String> getAllCustomers() {
        return customerRepository.getAllUsernames();
    }
}
