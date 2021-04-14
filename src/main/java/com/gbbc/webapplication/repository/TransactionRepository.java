package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findTransactionByDebetAccount_IdOrCreditAccount_Id(int debetAccount_id, int creditAccount_id);

    List<Transaction> findTransactionByDebetAccount_IdOrCreditAccount_IdOrderByDateDesc(int debetAccount_id, int creditAccount_id);
}
