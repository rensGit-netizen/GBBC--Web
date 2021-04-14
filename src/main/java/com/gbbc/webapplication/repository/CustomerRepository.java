package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author R. Portzgen, E. Koo, I. Ben Cherif
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("select c from Customer c where c.userName = :user_name")
    Customer searchByUserName(@Param("user_name") String user_name);

    @Query("select c from Customer c where c.id = :id")
    Customer searchCustomerById(@Param("id") int id);

    Customer findCustomerById(int id);

    Customer findCustomerByUserName(String username);

    @Query("select c.userName from Customer c")
    List<String> getAllUsernames();
}
