/**
 * @author E. Koo
 */
package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.services.interfaces.AuthorizePersonService;
import com.gbbc.webapplication.services.interfaces.OverviewViewServiceInterface;
import com.gbbc.webapplication.services.sessionServices.CustomerSessionService;
import com.gbbc.webapplication.viewmodel.Authorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthorizePersonController {

    @Autowired
    CustomerSessionService customerSessionService;

    @Autowired
    OverviewViewServiceInterface overviewViewServiceInterface;

    @Autowired
    AuthorizePersonService authorizePersonService;

    @GetMapping
    public String getUsernames(Model model){
        List<String> customerList = authorizePersonService.getAllCustomers();
        model.addAttribute("customer", customerList);
        return "customer";
    }

    @PostMapping("/person-authorization")
    public String authorizeCustomerBankAccount(@ModelAttribute("authorizePerson") Authorize authorize, HttpSession session) {
//        session.getAttribute("selected-iban");
//        System.out.println(session.getAttribute("selected-iban"));
        System.out.println("in authorize person controller");
        System.out.println(authorize.getFirst_name());
        if (session.getAttribute("userSession") != null) {
            System.out.println("In account overview");
            try {
                authorizePersonService.authorizePerson(authorize);
                System.out.println(authorize.getFirst_name());
                return "accountOverviewView";
            } catch (Exception e) {
                System.out.println(HttpStatus.CONFLICT);
                return String.valueOf(HttpStatus.CONFLICT);
            }
        }
        return "inlogView";
    }
}