package com.gbbc.webapplication.services.interfaces;
/**
 * @author Emily Koo
 * Test if Customer already exist in database
 * Test for adding a new bankAccount to an existing Customer
 */

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.impl.OpenAccountServiceImpl;
import com.gbbc.webapplication.viewmodel.OpenAccount;
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
public class OpenAccountServiceTest {

    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    BankaccountRepository bankaccountRepository;
    @InjectMocks
    OpenAccountServiceImpl openAccountService;

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
        mockBankAccount.setSaldo(new BigDecimal(0));
        mockBankAccount.setSector("Horeca");
        mockBankAccount.setType("Particulier");
        mockBankAccount.setTitle("Henkie Penkie");
        return mockBankAccount;
    }

    /**
     * Mock existing BankAccount
     * @return mocked BankAccount
     */
    public BankAccount bankAccountSetupWithId(){
        BankAccount mockBankAccount = bankAccountSetup();
        mockBankAccount.setId(10);
        return mockBankAccount;
    }

    /**
     * Mock open account
     * @return mocked open account
     */
    public OpenAccount openAccountSetup(){
        OpenAccount mockOpenAccount = new OpenAccount();
        mockOpenAccount.setUser_name("emily.pq.koo@gmail.com");
        return mockOpenAccount;
    }

    /**
     * @verifies be true if customer already exists
     *
     */
    @Test
    public void isAlreadyCustomer_shouldBeTrueIfCustomerAlreadyExists() throws Exception {
        boolean result;
        // arrange
        Mockito.when(customerRepository.findCustomerByUserName("emily.pq.koo@gmail.com")).thenReturn(customerSetup());
        // act
        result = openAccountService.isAlreadyCustomer("emily.pq.koo@gmail.com");
        // assert
        assertThat(result).isTrue();
    }

    /**
     * @verifies be false if username is not in database
     *
     */
    @Test
    public void isAlreadyCustomer_shouldBeFalseIfUsernameIsNotInDatabase() throws Exception {
        boolean result;
        // arrange
        Mockito.when(customerRepository.findCustomerByUserName("emily.pq.koo@gmail.com")).thenReturn(null);
        // act
        result = openAccountService.isAlreadyCustomer("emily.pq.koo@gmail.com");
        // assert
        assertThat(result).isFalse();
    }

    /**
     * @verifies add bankAccount to existing Customer
     * @see OpenAccountService#addExtraAccount(OpenAccount, Customer)
     */
    @Test
    public void addExtraAccount_shouldAddBankAccountToExistingCustomer() throws Exception {
        // arrange
        Mockito.when(customerRepository.findCustomerByUserName(customerSetup().getUserName())).thenReturn(customerSetupWithId());
        Mockito.when(customerRepository.save(customerSetup())).thenReturn(customerSetupWithId());
        Mockito.when(bankaccountRepository.save(bankAccountSetup())).thenReturn(bankAccountSetupWithId());
        // act
        Customer mockCustomer = customerSetupWithId();
        int amount = mockCustomer.getBankAccount().size();
        openAccountService.addExtraAccount(openAccountSetup(), mockCustomer);
        // assert
        assertThat(amount + 1).isEqualTo(mockCustomer.getBankAccount().size());
    }
}
