package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author R. Portzgen, E. Koo, I. Ben Cherif
 */

@Repository
public interface BankaccountRepository extends CrudRepository<BankAccount, Integer>{

    List<BankAccount> findTop10BySectorIsNotOrderBySaldoDesc(String sector);

    BankAccount findBankAccountById(int id);

    BankAccount findBankAccountByIban(String iban);

    /**
     * find iban containing ...........
     * @param iban accountnumber
     */
    BankAccount findBankAccountByIbanContaining(String iban);

    /**
     * find iban that ends with ...........
     * @param iban accountnumber
     */
    BankAccount findBankAccountByIbanEndsWith(String iban);

    /**
     * Find bankaccount by matching pin and ending of iban
     */
    BankAccount findBankAccountByIbanEndsWithAndPinNumber(String iban, int pinNumber);

    /**
     * Find bankAccounts with Authorized person
     * @return iban with authorizedPerson
     */
    BankAccount getBankAccountByAuthorizedPersonIsNotNull();

    @Query(value= "select * from bank_account b where type = 'Zakelijk'", nativeQuery=true)
    List<BankAccount> getAllBusinessAccounts();

    @Query(value= "select * from bank_account b where type = 'Particulier'", nativeQuery=true)
    List<BankAccount> getAllPrivateAccounts();

    List<BankAccount> findBankAccountsByCustomer(Customer customer);

    List<BankAccount> findBankAccountsByGemachtigde(Customer gemachtigde);

    @Query(value = "select bank_account_id from bank_account_transaction_history group by bank_account_id order by count(transaction_history_id) desc limit 50", nativeQuery=true)
    List<Integer> get50MostActiveBankAccountIds();

    @Query(value = "select ba.iban from bank_account ba INNER JOIN transaction tr ON ba.id=tr.debet_account_id OR ba.id=tr.credit_account_id WHERE ba.type='zakelijk' group by ba.id ORDER BY COUNT(ba.id) DESC LIMIT 10", nativeQuery=true)
    List<String> getNew10MostActiveBankAccountIban();


    @Query(value = "select sector from bank_account where not sector like 'prive' group by sector order by AVG(saldo) desc", nativeQuery = true)
    public List<String> getAllSegmentNames();


    @Query(value = "select round(AVG(saldo),2) from bank_account where not sector like 'prive' group by sector order by AVG(saldo) desc", nativeQuery = true)
    public List<String> getAllSegmentAvgs();
}
