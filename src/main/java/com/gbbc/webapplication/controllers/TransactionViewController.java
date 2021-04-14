package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.services.interfaces.TransactionViewControllerServiceInterface;
import com.gbbc.webapplication.viewmodel.TransactionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class TransactionViewController {

    @Autowired
    TransactionViewControllerServiceInterface transactionViewControllerServiceInterface;

    @PostMapping("/transactionViewMapping")
    public String transactionViewMapping(@ModelAttribute("transactionViewModel") TransactionViewModel transaction, Model model){
        System.out.println("In transactionViewController");
        String returnValue = transactionViewControllerServiceInterface.getTransactionView(transaction, model);
        if (returnValue == null) return "transactionView";
        return returnValue;
    }
}
