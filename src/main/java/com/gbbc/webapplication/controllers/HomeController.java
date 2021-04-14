/**
 * @author E. Koo
 */
package com.gbbc.webapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    /**
     * Home Pagina
     *
     * @return homeView.jsp
     */
    @GetMapping("/home")
    public String goHome() {
        System.out.println("GoldBarBankingCo");
        return "homeView";
    }

    @GetMapping("/RestControllerTest")
    public String goToRestControllerTest(){
        System.out.println("Test RestController");
        return "RestControllerTest";
    }

    /**
     * Bezoeker: Open rekening pagina
     *
     * @return openAccountView.jsp
     */
    @GetMapping("/open-account")
    public String goToOpenAccount() {
        System.out.println("In open account view");
        return "openAccountView";
    }

    @GetMapping("/account-statement-view")
    public String goToAccountStatementView() {
        System.out.println("In open account statement view");
        return "accountStatementView";
    }


    /**
     * Login Pagina
     *
     * @return inlogView.jsp
     */
    @GetMapping("/login")
    public String goToLogin() {
        System.out.println("in login view");
        return "inlogView";
    }

    /**
     * Rekening:    overzicht van alle rekeningen van ingelogde klant
     *
     * @return accountOverviewView.jsp
     */

    /**
     * Home Controller
     * Rekening:    Persoon machtigen
     *
     * @return accountStatementView.jsp
     */
    @GetMapping("/authorize")
    public String goToAuthorize(HttpSession hs) {
/*        System.out.println("in selected authorize view");
        Object iban = hs.getAttribute("selectedIban");
        System.out.println("dit is mijn iban: "+ iban);*/
        return "authorizeUserView";
    }


    /**
     * Rekening:    Koppel rekening
     *
     * @return linkAccountView.jsp
     */
    @GetMapping("/account-link")
    public String goToLinkAccount() {
        System.out.println("in link account view");
        return "linkAccountView";
    }

    /**
     * Transactie:  Doe transactie pagina
     *
     * @return transactionView.jsp
     */
    @GetMapping("/transaction")
    public String goToTransaction() {
        System.out.println("in do transaction view");
        return "transactionView";
    }

    /**
     * Transactie:  Doe transactie pagina
     *
     * @return transactionView.jsp
     */
    @GetMapping("/employeeControle")
    public String goToInLogViewEmployee() {
        System.out.println("in employeeControle");
        return "inlogViewEmployee";
    }


    /**
     * Transactie:  Overzicht van de te maken transactie,
     * bevestig of annuleer transactie
     *
     * @return transactionConfirmationView.jsp
     */
    @GetMapping("/transaction-confirmation")
    public String goToTransactionConfirmation() {
        System.out.println("in transaction confirmation");
        return "transactionConfirmationView";
    }

    /**
     * Transactie:  Bevestiging van geslaagde transactie
     *
     * @return transactionSucceedView
     */
    @GetMapping("/transaction-succeeded")
    public String goToTransactionSucceeded() {
        System.out.println("in transaction succeeded");
        return "transactionSucceededView";
    }


    @GetMapping("/request-terminal")
    public String goToRequestTerminal() {
        System.out.println("in request terminal");
        return "requestTerminal";
    }


}
