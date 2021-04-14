package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.repository.BankaccountRepository;
import com.gbbc.webapplication.services.interfaces.AccountHolderService;
import com.gbbc.webapplication.viewmodel.AddAccountHolderRequest;
import com.gbbc.webapplication.services.sessionServices.CustomerSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Controller connecting Accounts
 * addAccountHolder.jsp
 * linkAccountView.jsp
 *
 * @author R. Portzgen
 */
@Controller
public class LinkAccountHolderController {
    @Autowired
    BankaccountRepository bankaccountRepository;
    @Autowired
    CustomerSessionService customerSessionService;
    @Autowired
    AccountHolderService accountHolderService;


    @GetMapping("/goToConnectAccount")
    public String linkAccount(HttpSession session) {
        if (session.getAttribute("userSession")!=null) {
        return "linkAccountView";
        } else return "inlogView";
    }

    @PostMapping("/connectAccount")
    public String connectAccountToCustomer(@ModelAttribute("AddAccountHolderRequest")AddAccountHolderRequest request, Model model, HttpSession session) {
        if (session.getAttribute("userSession")!=null) {
            String returnValue = "accountOverviewView";
            Customer customer = (Customer)session.getAttribute("userSession");
            returnValue = accountHolderService.processRequest(request, model, customer, session);
            System.out.println("ready to connect a account to this customer");
            return returnValue;
        } else return "inlogView";
    }


    @PostMapping("/goToAddAccountHolder")
    public String addAccountHolder(@ModelAttribute("AddAccountHolderRequest")AddAccountHolderRequest request, Model model) {
        model.addAttribute("iban", request.getIban());
        return "addAccountHolder";
    }

    @PostMapping("/addAccountHolder")
    public String addAccountHolderToAccount(@ModelAttribute("AddAccountHolderRequest")AddAccountHolderRequest request, Model model, HttpSession session) {
        if (session.getAttribute("userSession")!=null) {
            String returnValue = "forward:/overviewViewFormHandler";
            Customer loggedInCustomer = (Customer)session.getAttribute("userSession");

            returnValue = accountHolderService.createAccountRequest(request, model, returnValue, loggedInCustomer, session);
            System.out.println("ready to connect a customer to this account");
            model.addAttribute("account", bankaccountRepository.findBankAccountByIban(request.getIban()));
            model.addAttribute("iban", request.getIban());
            return returnValue;
        } else return "inlogView";
    }

}
