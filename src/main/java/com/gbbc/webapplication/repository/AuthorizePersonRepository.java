package com.gbbc.webapplication.repository;

import com.gbbc.webapplication.beans.AuthorizedPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Emily Koo
 */

@Repository
public interface AuthorizePersonRepository extends CrudRepository<AuthorizedPerson, Integer> {

    /**
     * find authorized person by id
     * @param id authorized person id
     * @return authorized person information
     */
    AuthorizedPerson findAuthorizedPersonById(int id);
}
