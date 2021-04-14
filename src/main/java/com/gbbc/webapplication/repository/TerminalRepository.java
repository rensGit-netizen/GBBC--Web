package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.beans.TerminalRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author R. Portzgen, E. Koo
 */
@Repository
public interface TerminalRepository extends CrudRepository<TerminalRequest,Integer> {
    List<TerminalRequest> getAllByOrderByStatusAsc();

//    @Query(value= "select * from terminal_request where bank_account_id = 'prive'", nativeQuery=true)
//    List<BankAccount> getAllBusinessAccounts();

    TerminalRequest findByBankAccountId(int id);

    /**
     * Get all controlcodes
     * @return list of generated control codes
     */
    @Query("select t.controlCode from TerminalRequest t")
    List<String> getAllControlCodes();

    /**
     * Get TerminalRequest by BankAccount and ControlCode
     * @param iban bank
     * @param code to link atm
     * @return valid combination
     */
    TerminalRequest getTerminalRequestByBankAccountLikeAndControlCode(BankAccount iban, int code);
}
