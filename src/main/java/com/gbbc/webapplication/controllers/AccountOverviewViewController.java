/**
 * @Entity bean for Bank Account Data
 * @author I. Ben Cherif, E. Koo
 */
package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.services.interfaces.OverviewViewServiceInterface;
import com.gbbc.webapplication.services.sessionServices.CustomerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountOverviewViewController {

    @Autowired
    CustomerSessionService customerSessionService;

    @Autowired
    OverviewViewServiceInterface overviewViewServiceInterface;

    @PostMapping("/overviewViewFormHandler")
    public String overviewViewFormHandler(@ModelAttribute("account")BankAccount bankaccount, Model model, HttpSession session) {
        System.out.println(bankaccount.getId() + " bankaccount Id");
        String returnValue = overviewViewServiceInterface.getOverviewView(bankaccount, model, session);
        return returnValue;
    }

    @GetMapping("/goToAccountOverView")
    public String goToAccountOverview(HttpSession session) {
        if (session.getAttribute("userSession") != null) {
            return "accountOverviewView";
        } else return "inlogView";
    }

}
