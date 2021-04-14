package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.services.interfaces.OpenAccountService;
import com.gbbc.webapplication.viewmodel.OpenAccount;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author E. Koo
 */

@Service
public class OpenAccountServiceImpl implements OpenAccountService {

    @Autowired
    private final BankaccountRepository bankaccountRepository;
    @Autowired
    private final CustomerRepository customerRepository;

    public OpenAccountServiceImpl(BankaccountRepository bankaccountRepository, CustomerRepository customerRepository) {
        this.bankaccountRepository = bankaccountRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public boolean isAlreadyCustomer(String username) {
        boolean isExistingCustomer;
        Customer isValidCustomer = customerRepository.findCustomerByUserName(username);
        isExistingCustomer = isValidCustomer != null;
        /*        if(isValidCustomer == null){
            isExistingCustomer = false;
        } else {
            isExistingCustomer = true;
        }*/
        return isExistingCustomer;
    }

    @Override
    public void addExtraAccount(OpenAccount openExtraAccount, Customer owner) {
        BankAccount addExtraBankAccount = new BankAccount();
        List<Customer> customerList = new ArrayList<>();
        if(isAlreadyCustomer(owner.getUserName())){
            // set customer id
            customerList.add(owner);
            addExtraBankAccount.setCustomer(customerList);
            // set bankaccount attributes
            addExtraBankAccount.setType(openExtraAccount.getCustomer_type());
            addExtraBankAccount.setTitle(openExtraAccount.getTitle());
            addExtraBankAccount.setSector(openExtraAccount.getSector());
            addExtraBankAccount.setSaldo(new BigDecimal(1000));
            // create new bankaccount
            addExtraBankAccount.setIban(generateIban());
            addExtraBankAccount.setPinNumber(generateBankAccountPin());
            // save bankaccount
            bankaccountRepository.save(addExtraBankAccount);
            // add customer to BankAccount list
            owner.addBankAccount(addExtraBankAccount);
            /*            List<BankAccount> bankAccountList = owner.getBankAccount();
            bankAccountList.add(addExtraBankAccount);
            owner.setBankAccount(bankAccountList);*/
            customerRepository.save(owner);
        }
    }

    @Override
    public void createAccount(OpenAccount storeAccount) {
        //Create a check Customer
        Customer checkCustomer = customerRepository.searchByUserName(storeAccount.getUser_name());
        //Check if customer exist
        if (checkCustomer != null) {
            addExtraAccount(storeAccount, checkCustomer);
        }
        //if not exist
        if (checkCustomer == null) {
            //set new Customer values with attributes from storeAccount
            checkCustomer = setCustomer(storeAccount);
            //set new BankAccount values with attributes from storeAccount
            BankAccount createBankAccount = setBankAccount(storeAccount);
            //add account to customer
            checkCustomer.addBankAccount(createBankAccount);
            /*List<BankAccount> allAccountsFromCustomer = new ArrayList<>();
            allAccountsFromCustomer.add(createBankAccount);
            checkCustomer.setBankAccount(allAccountsFromCustomer);*/

            //add customer to account
            createBankAccount.addCustomer(checkCustomer);
            /*            List<Customer> allCustomersAccount = new ArrayList<>();
            allCustomersAccount.add(checkCustomer);
            createBankAccount.setCustomer(allCustomersAccount);*/

            //save customer to repo
            customerRepository.save(checkCustomer);
            //save account to repo
            bankaccountRepository.save(createBankAccount);
        }
    }

    private BankAccount setBankAccount(OpenAccount storeAccount) {
        BankAccount createBankAccount = new BankAccount();
        createBankAccount.setType(storeAccount.getCustomer_type());
        createBankAccount.setTitle(storeAccount.getTitle());
        if(storeAccount.getSector()==null){
            createBankAccount.setSector("prive");
        } else{
            createBankAccount.setSector(storeAccount.getSector());
        }
        createBankAccount.setIban(generateIban());
        storeAccount.setIban(createBankAccount.getIban());
        createBankAccount.setSaldo(new BigDecimal(1000.00));
        createBankAccount.setPinNumber(generateBankAccountPin());
        return createBankAccount;
    }

    private int generateBankAccountPin(){
        int returnValue = Integer.MAX_VALUE;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int number = random.nextInt(10);
            stringBuilder.append(number);
        }
        returnValue = Integer.parseInt(stringBuilder.toString());
        return returnValue;
    }

    /**
     * Set new customer values if customer doesn't exist in database
     *
     * @param storeAccount field values from open account form
     * @return setCustomer
     */
    @Override
    public Customer setCustomer(OpenAccount storeAccount) {
        Customer checkCustomer;
        checkCustomer = new Customer();
        checkCustomer.setFirst_name(storeAccount.getFirst_name());
        checkCustomer.setLast_name(storeAccount.getLast_name());
        checkCustomer.setBsn(storeAccount.getBsn());
        checkCustomer.setDate_of_birth(storeAccount.getDate_of_birth());
        checkCustomer.setStreet(storeAccount.getStreet());
        checkCustomer.setPostal_code(storeAccount.getPostal_code());
        checkCustomer.setCity(storeAccount.getCity());
        checkCustomer.setCountry(storeAccount.getCountry());
        checkCustomer.setUserName(storeAccount.getUser_name());
        checkCustomer.setPassword(storeAccount.getPassword());
        return checkCustomer;
    }


    /**
     * Iban generator
     * @return iban
     */
    @Override
    public String generateIban(){
        Random random = new Random();
        StringBuilder countryCode = new StringBuilder("NL");
        StringBuilder bankName = new StringBuilder("GBBC");

        generateStringDigits(random, countryCode, 2);
        generateStringDigits(random, bankName, 10);

        return countryCode + bankName.toString();
    }

    /**
     * digits generator
     * @param random random digits
     * @param numberString string of random digits
     * @param amount amount of numbers
     */
    @Override
    public void generateStringDigits(Random random, StringBuilder numberString, int amount) {
        int targetStringLength2;
        for (int i = 0; i < amount; i++) {
            targetStringLength2 = random.nextInt(10);
            numberString.append(targetStringLength2);
        }
    }
}