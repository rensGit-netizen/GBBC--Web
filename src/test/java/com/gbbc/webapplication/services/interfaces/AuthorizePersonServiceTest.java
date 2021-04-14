package com.gbbc.webapplication.services.interfaces;
/**
 * @author Emily Koo
 */

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.impl.AuthorizePersonServiceImpl;
import com.gbbc.webapplication.viewmodel.Authorize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class AuthorizePersonServiceTest {

    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    BankaccountRepository bankaccountRepository;
    @InjectMocks
    AuthorizePersonServiceImpl authorizePersonService;

    /**
     * Mock new Customer
     * @return mocked Customer
     */
    public Customer customerSetup() {
        Customer mockCustomer = new Customer();
        mockCustomer.setUserName("emily.pq.koo@gmail.com");
        return mockCustomer;
    }

    /**
     * Mock existing customer
     * @return mocked Customer
     */
    public Customer customerSetupWithId() {
        Customer mockCustomer = customerSetup();
        mockCustomer.setId(2);
        return mockCustomer;
    }

    /**
     * Mock new BankAccount
     * @return mocked BankAccount
     */
    public BankAccount bankAccountSetup(){
        List<Customer> customerList = new ArrayList<>();
        BankAccount mockBankAccount = new BankAccount();
        mockBankAccount.setCustomer(customerList);
        mockBankAccount.setGemachtigde(customerSetupWithId());
        return mockBankAccount;
    }

    /**
     * Mock existing BankAccount
     * @return mocked BankAccount
     */
    public BankAccount bankAccountSetupWithId(){
        Customer gemachtigde = new Customer();
        BankAccount mockBankAccount = bankAccountSetup();
        mockBankAccount.setGemachtigde(gemachtigde);
        return mockBankAccount;
    }

    /**
     * Mock authorization
     * @return mocked authorization
     */
    public Authorize authorizeSetup(){
        Authorize mockAuthorize = new Authorize();
        mockAuthorize.setUser_name("emily.pq.koo@gmail.com");
        return mockAuthorize;
    }

    /**
     * @verifies add authorization to existing Customer
     * @see AuthorizePersonService#addAuthorizedCustomerToBankAccount(com.gbbc.webapplication.viewmodel.Authorize, com.gbbc.webapplication.beans.BankAccount, com.gbbc.webapplication.beans.Customer)
     */
    @Test
    public void addAuthorizedCustomerToBankAccount_shouldAddAuthorizationToExistingCustomer() throws Exception {
        // arrange
        Mockito.when(customerRepository.findCustomerByUserName(customerSetup().getUserName())).thenReturn(customerSetupWithId());
        Mockito.when(customerRepository.save(customerSetup())).thenReturn(customerSetupWithId());
        Mockito.when(bankaccountRepository.save(bankAccountSetup())).thenReturn(bankAccountSetupWithId());
        // act
        Customer mockCustomer = customerSetupWithId();
        BankAccount mockBankAccount = bankAccountSetupWithId();
        int id = mockBankAccount.getGemachtigde().getId();

        authorizePersonService.addAuthorizedCustomerToBankAccount(authorizeSetup(), mockBankAccount, mockCustomer);
        // assert
        assertThat(id).isEqualTo(mockBankAccount.getGemachtigde());
    }

    /**
     * @verifies add authorized Customer to BankAccount
     * @see AuthorizePersonService#addAuthorizedCustomerToBankAccount(com.gbbc.webapplication.viewmodel.Authorize, com.gbbc.webapplication.beans.BankAccount, com.gbbc.webapplication.beans.Customer)
     */
    @Test
    public void addAuthorizedCustomerToBankAccount_shouldAddAuthorizedCustomerToBankAccount() throws Exception {
        // arrange

        // act

        // assert
    }
}
