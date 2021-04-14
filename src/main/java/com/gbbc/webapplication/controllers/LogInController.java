package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.services.impl.LogInServiceImpl;
import com.gbbc.webapplication.services.interfaces.LogInServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogInController {

    @Autowired
    public LogInServiceInterface logInServiceInterface;

    public LogInController(LogInServiceImpl customerRepository) {
        super();
        this.logInServiceInterface = customerRepository;
    }

    /**
     * @param fieldCustomer
     * @param model
     * @return
     * @should get propper information and send the right information
     */
    @PostMapping("/loginControle")
    public String login(@ModelAttribute("customer")Customer fieldCustomer, Model model, HttpSession session) {
        String returnValue = "accountOverviewView";
        if (fieldCustomer != null) {
            returnValue = logInServiceInterface.compareFieldWithDatabase(fieldCustomer, model, returnValue, session);
        } else {
            returnValue = "inlogView";
        }
        return returnValue;
    }


}