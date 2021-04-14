package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("select e from Employee e where e.userName = :user_name")
    Employee searchByEmployeeUserName(@Param("user_name") String user_name);


}
