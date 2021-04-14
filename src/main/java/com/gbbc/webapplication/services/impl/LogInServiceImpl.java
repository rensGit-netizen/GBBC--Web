package com.gbbc.webapplication.services.impl;

import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.CustomerRepository;
import com.gbbc.webapplication.services.interfaces.LogInServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class LogInServiceImpl implements LogInServiceInterface {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String compareFieldWithDatabase(Customer fieldCustomer, Model model, String returnValue, HttpSession session) {
        Customer checkCustomer = customerRepository.searchByUserName(fieldCustomer.getUserName());
        if (checkCustomer==null)
        {
            returnValue = checkIfPasswordsMatchOrCustomerIsNull(model);
        }
        else if (!fieldCustomer.getPassword().equals(checkCustomer.getPassword()))
        {
            returnValue = checkIfPasswordsMatchOrCustomerIsNull(model);
        }
        else {
            session.setAttribute("userSession", checkCustomer);
            model.addAttribute("customer", checkCustomer);
        }
            return returnValue;

    }

    @Override
    public String checkIfPasswordsMatchOrCustomerIsNull(Model model) {
        String returnValue;
        model.addAttribute("invalidLogin", "Controleer je wachtwoord en gebruikersnaam.");
        model.addAttribute("customer", new Customer());
        returnValue = "inlogView";
        return returnValue;
    }

}
