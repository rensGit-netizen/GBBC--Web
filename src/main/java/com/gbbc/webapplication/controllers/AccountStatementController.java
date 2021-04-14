package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.BankAccount;
import com.gbbc.webapplication.services.interfaces.StatementServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AccountStatementController {

    @Autowired
    public StatementServiceInterface statementServiceInterface;

    /**
     * Rekening:    Toon geselecteerde rekening
     *
     * @return accountStatementView.jsp
     */
    @GetMapping("/account-statement")
    public String goToAccountStatement(HttpSession session, Model model)
    {
        if (session.getAttribute("userSession")==null){
            return "inlogView";
        }
        System.out.println("in selected account statement view");
        return "accountStatementView";
    }

    @PostMapping("/toTransactions")
    public String toTransactions(@ModelAttribute("account")BankAccount bankAccount, Model model){
        System.out.println("in AccountStatementController");
        String returnValue = statementServiceInterface.getTransactionOverview(bankAccount, model);

        return returnValue;
    }

}
