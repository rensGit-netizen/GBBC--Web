package com.gbbc.webapplication.services.sessionServices;

import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.beans.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CustomerSessionService {
    @Autowired
    HttpSession session;

    public Boolean isLoggedIn() {
        boolean isLoggedIn = false;
        if (session.getAttribute("isLoggedIn") != null)
            isLoggedIn = (boolean) session.getAttribute("isLoggedIn");
        return isLoggedIn;
    }

    public Customer getLoggedInUser() {
        Customer customer = new Customer();
        if (session.getAttribute("sessionUser") != null)
            customer = (Customer)session.getAttribute("sessionUser");
        return customer;
    }

}

