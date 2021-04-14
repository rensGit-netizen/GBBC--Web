package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.services.interfaces.BusinessService;
import com.gbbc.webapplication.services.sessionServices.EmployeeSessionService;
import com.gbbc.webapplication.viewmodel.Business;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
/**
 * Controller for 'HoofdParticulieren'
 * privateBankingView.jsp
 *
 * @author R. Portzgen
 */
@Controller
public class PrivateBankingController {
    @Autowired
    public BusinessService businessService;

    @Autowired
    EmployeeSessionService employeeSessionService;

    /**
     * Medewerker Hoofd Particulieren: Particulieren overzichtspagina
     *
     * @return privateBankingView.jsp
     */
    @GetMapping("/private-banking-customers")
    public String goToPrivateBanking(Model model) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("going to Private Customers");
            String employeeName = employeeSessionService.getLoggedInUser().getFirst_name();

            List<Business> allBusinessAccounts = businessService.getPrivateBankAccounts();
            model.addAttribute("accounts", allBusinessAccounts);
            model.addAttribute("employee", employeeName);
            return "privateBankingView";
        } else return "inlogViewEmployee";
    }


}
