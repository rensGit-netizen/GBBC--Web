package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.impl.AccountHolderServiceImpl;
import com.gbbc.webapplication.services.impl.BusinessServiceImpl;
import com.gbbc.webapplication.viewmodel.AddAccountHolderRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;
/**
 * @author Rens Portzgen
 *
 */
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class AccountHolderServiceTest {
    @MockBean
    BankaccountRepository bankaccountRepository;

    @MockBean
    CustomerRepository customerRepository;

    @InjectMocks
    AccountHolderServiceImpl accountHolderService;


    /**
     * @return Mock BankAccount with known IBAN
     */
    public BankAccount createDummyAccount() {
        BankAccount testAccount = new BankAccount();
        testAccount.setIban("NL88GBBC888888888");
        return testAccount;
    }

    /**
     * @return Mock AddAccountHolderRequest with know IBAN
     */
    public AddAccountHolderRequest createDummyRequest() {
        AddAccountHolderRequest request = new AddAccountHolderRequest();
        request.setIban(createDummyAccount().getIban());
        return request;
    }


    /**
     *
     * @return Mock Customer
     */
    public Customer createDummyCustomer(){
        Customer testCustomer = new Customer();
        List<BankAccount> testCustomerAccounts = new ArrayList<>();
        testCustomer.setId(1);
        testCustomer.setBankAccount(testCustomerAccounts);
        return testCustomer;
    }


    /**
     * @verifies return false when Customer is not an Accountholder of given BankAccount
     * @see AccountHolderService#isAccountHolder(com.gbbc.webapplication.beans.BankAccount, com.gbbc.webapplication.beans.Customer)
     */
    @Test
    public void isAccountHolder_shouldReturnFalseWhenCustomerIsNotAnAccountholderOfGivenBankAccount() throws Exception {
    // Arrange
        BankAccount testAccount = createDummyAccount();
        Customer testCustomer = createDummyCustomer();
        testAccount.addCustomer(testCustomer);
        // Act
       boolean isAccountHolder = accountHolderService.isAccountHolder(testAccount,testCustomer);
        // Assert
        assertThat(isAccountHolder).isTrue();
    }

    /**
     * @verifies return true when Customer is an Accountholder of given BankAccount
     * @see AccountHolderService#isAccountHolder(com.gbbc.webapplication.beans.BankAccount, com.gbbc.webapplication.beans.Customer)
     */
    @Test
    public void isAccountHolder_shouldReturnTrueWhenCustomerIsAnAccountholderOfGivenBankAccount() throws Exception {
        // Arrange
        BankAccount testAccount = createDummyAccount();
        Customer testCustomer = createDummyCustomer();
        // Act
        boolean isAccountHolder = accountHolderService.isAccountHolder(testAccount,testCustomer);
        // Assert
        assertThat(isAccountHolder).isFalse();
    }

    /**
     * @verifies add the given bankaccount to the given customer
     * @see AccountHolderService#handleRequest(com.gbbc.webapplication.viewmodel.AddAccountHolderRequest, com.gbbc.webapplication.beans.Customer)
     */
    @Test
    public void handleRequest_shouldAddTheGivenBankaccountToTheGivenCustomer() throws Exception {
        // Arrange
        Customer testCustomer = createDummyCustomer();

        int length = testCustomer.getBankAccount().size();
        Mockito.when(bankaccountRepository.findBankAccountByIban(createDummyAccount().getIban())).thenReturn(createDummyAccount());
        Mockito.when(customerRepository.searchCustomerById(testCustomer.getId())).thenReturn(testCustomer);
        // Act
        accountHolderService.handleRequest(createDummyRequest(),testCustomer);

        // Assert
        assertThat(length+1).isEqualTo(testCustomer.getBankAccount().size());

    }
}
