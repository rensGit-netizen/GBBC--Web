/**
 * @author E. Koo
 */
package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.Customer;
import com.gbbc.webapplication.services.interfaces.OpenAccountService;
import com.gbbc.webapplication.viewmodel.OpenAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class OpenAccountController {

    @Autowired
    public OpenAccountService openAccountService;

    /**
     * Bezoeker:    Rekening is succesvol aangemaakt
     * Laat overzicht zien van ingevulde gegevens
     *
     * @return succesfullyRegisteredAccountView.jsp
     */
    @GetMapping("/account-created-succes")
    public String goToAccountCreated(Model model) {
        System.out.println("In succesfully created account");

        return "succesfullyRegisteredAccountView";
    }

    @PostMapping("/account-created-succes")
    public String createCustomer(@ModelAttribute("openAccount") OpenAccount openAccount, ModelMap model) {
        // check form
        openAccountService.createAccount(openAccount);
        System.out.println(openAccount.getIban());
        return "succesfullyRegisteredAccountView";
    }

    @PostMapping("/extra-account-created")
    public String createExtraBankaccount(@ModelAttribute("openAccount") OpenAccount openAccount, HttpSession session){
        if(session.getAttribute("userSession") != null){
            System.out.println("In account overview");
            openAccountService.addExtraAccount(openAccount, (Customer) session.getAttribute("userSession"));
            System.out.println("extra account created "+ openAccount.getIban());
            return "accountOverviewView";
        }
        return String.valueOf(HttpStatus.CONFLICT);
    }

}
