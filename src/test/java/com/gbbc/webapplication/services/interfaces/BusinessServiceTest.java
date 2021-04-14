package com.gbbc.webapplication.services.interfaces;
import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.impl.BusinessServiceImpl;
import com.gbbc.webapplication.viewmodel.Business;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Rens Portzgen
 *
 */
@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class BusinessServiceTest {
    @MockBean
    BankaccountRepository bankaccountRepository;

    @MockBean
    CustomerRepository customerRepository;

    @InjectMocks
    BusinessServiceImpl businessService;


    /**
     * Mock length of Business Account
     *
     */
    public int mockLengthOfList(){
        int randomAmountOfAccounts = (int)(1+(Math.random()*10));
        return randomAmountOfAccounts;
    }

    /**
     * Mock BankAccount with know data
     */
    public BankAccount bankAccountDummy(){
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
     * Mock list of BankAccounts with 5 bankaccounts
     */
    public List<BankAccount> bankAccountArrayList(int lengte){
        List<BankAccount> mockListOfbankAccounts = new ArrayList<>();
        for (int i = 0; i < lengte; i++) {
            mockListOfbankAccounts.add(bankAccountDummy());
        }
        return mockListOfbankAccounts;
    }


    /**
     * Mock List of Business Objects
     * @return
     */
    public List<Business> businessListDummy(int lengte){
        List<Business> mockBusiness = new ArrayList<>();
        for (int i = 0; i < lengte; i++) {
            mockBusiness.add(new Business());
        }
        return mockBusiness;
    }




    /**
     * @verifies get an Arraylist of all business Accounts from DB
     * @see BusinessService#getBankAccounts()
     */
    @Test
    public void getBankAccounts_shouldGetAnArraylistOfAllBusinessAccountsFromDB() throws Exception {
        // arrange
        int lengte = mockLengthOfList();
        Mockito.when(bankaccountRepository.getAllBusinessAccounts()).thenReturn(bankAccountArrayList(lengte));
        // act
        int result = bankaccountRepository.getAllBusinessAccounts().size();
        // assert
        assertThat(result).isEqualTo(lengte);
    }

    /**
     * @verifies store all bankAccount data in Arraylist of Business Object
     * @see BusinessService#getBankAccounts()
     */
    @Test
    public void getBankAccounts_shouldStoreAllBankAccountDataInArraylistOfBusinessObject() throws Exception {
        // arrange
        int lengte = mockLengthOfList();
        Mockito.when(businessService.getBankAccounts()).thenReturn(businessListDummy(lengte));
        // act
        int result = bankaccountRepository.getAllBusinessAccounts().size();
        // assert
        assertThat(result).isEqualTo(lengte);
    }

    /**
     * @verifies get an Arraylist of business Accounts from DB
     * @see BusinessService#getQuoteTen()
     */
    @Test
    public void getQuoteTen_shouldGetAnArraylistOfBusinessAccountsFromDB() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies store bankAccount data in Business Object
     * @see BusinessService#getQuoteTen()
     */
    @Test
    public void getQuoteTen_shouldStoreBankAccountDataInBusinessObject() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies store the Business Objects in an Arraylist
     * @see BusinessService#getQuoteTen()
     */
    @Test
    public void getQuoteTen_shouldStoreTheBusinessObjectsInAnArraylist() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies get an Arraylist of business Accounts from DB
     * @see BusinessService#getMostActiveAccounts()
     */
    @Test
    public void getMostActiveAccounts_shouldGetAnArraylistOfBusinessAccountsFromDB() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies store bankAccount data in Business Object
     * @see BusinessService#getMostActiveAccounts()
     */
    @Test
    public void getMostActiveAccounts_shouldStoreBankAccountDataInBusinessObject() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies store the Business Objects in an Arraylist
     * @see BusinessService#getMostActiveAccounts()
     */
    @Test
    public void getMostActiveAccounts_shouldStoreTheBusinessObjectsInAnArraylist() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies get an Arraylist of segmentnames  from DB
     * @see BusinessService#getAllSectorsWithAvg()
     */
    @Test
    public void getAllSectorsWithAvg_shouldGetAnArraylistOfSegmentnamesFromDB() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies get an Arraylist of segment averages  from DB
     * @see BusinessService#getAllSectorsWithAvg()
     */
    @Test
    public void getAllSectorsWithAvg_shouldGetAnArraylistOfSegmentAveragesFromDB() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies store the data in Sector Object
     * @see BusinessService#getAllSectorsWithAvg()
     */
    @Test
    public void getAllSectorsWithAvg_shouldStoreTheDataInSectorObject() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }

    /**
     * @verifies store the Sector Objects in an Arraylist
     * @see BusinessService#getAllSectorsWithAvg()
     */
    @Test
    public void getAllSectorsWithAvg_shouldStoreTheSectorObjectsInAnArraylist() throws Exception {
        //TODO auto-generated
        Assertions.fail("Not yet implemented");
    }
}
