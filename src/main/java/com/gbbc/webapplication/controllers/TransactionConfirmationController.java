package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.services.interfaces.TransactionConfirmationServiceInterface;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TransactionConfirmationController {

    @Autowired
    TransactionConfirmationServiceInterface transactionConfirmationServiceInterface;

    @PostMapping("/transactionConfirmationController")
    public String transactionConfirmationController(@ModelAttribute("transactionViewModel") TransactionViewModel transaction, Model model, HttpSession session){
        String returnValue = transactionConfirmationServiceInterface.getTransactionInformation(transaction, model);
        return returnValue;
    }
}
