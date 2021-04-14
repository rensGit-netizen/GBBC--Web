package com.gbbc.webapplication.services.interfaces;

import com.gbbc.webapplication.beans.Customer;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface LogInServiceInterface {
    String compareFieldWithDatabase(Customer fieldCustomer, Model model, String returnValue, HttpSession session);

    String checkIfPasswordsMatchOrCustomerIsNull(Model model);
}
