package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.AccountRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLinkRepository extends CrudRepository<AccountRequest, Integer> {
   AccountRequest findById(int id);
   AccountRequest findByBankAccountId(int id);
   AccountRequest findByBankAccount_Iban(String iban);
   AccountRequest findByCustomerId(int id);


}
