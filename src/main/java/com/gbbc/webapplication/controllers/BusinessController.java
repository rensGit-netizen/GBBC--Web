package com.gbbc.webapplication.controllers;


import com.gbbc.webapplication.services.interfaces.BusinessService;
import com.gbbc.webapplication.viewmodel.Business;
import com.gbbc.webapplication.viewmodel.Sector;
import com.gbbc.webapplication.services.sessionServices.EmployeeSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Controller for all views for 'HoofdMKB'
 * smesView.jsp
 * sector.jsp
 *
 * @author R. Portzgen
 */
@Controller
public class BusinessController {
    @Autowired
    public BusinessService businessService;

    @Autowired
    EmployeeSessionService employeeSessionService;


    @GetMapping("/smes-customers")
    public String goToSmes(Model model, HttpSession employeeSession) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("going to SMEs Customers");
            String employeeName = employeeSessionService.getLoggedInUser().getFirst_name();

            List<Business> allBusinessAccounts = businessService.getBankAccounts();
            model.addAttribute("accounts", allBusinessAccounts);
            model.addAttribute("employee", employeeName);
            return "smesView";
        } else return "inlogViewEmployee";
    }


    @GetMapping("/top10")
    public String goToTop10(Model model, HttpSession employeeSession) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("going to top10(ibiza)");
            List<Business> top10Accounts = businessService.getQuoteTen();
            model.addAttribute("accounts", top10Accounts);
            model.addAttribute("isLoggedIn", (employeeSession.getAttribute("isLoggedIn") != null));
            return "smesView";
        } else return "inlogViewEmployee";
    }


    @GetMapping("/mostActive")
    public String goToMostActive(Model model, HttpSession employeeSession) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("going to mostActive");
            List<Business> tenMostActiveBusinessAccounts = businessService.getMostActiveAccounts();
            model.addAttribute("accounts", tenMostActiveBusinessAccounts);
            return "smesView";
        } else return "inlogViewEmployee";
    }


    @GetMapping("/averageSector")
    public String goToAverageSector(Model model, HttpSession employeeSession) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("going to AVGSectorSituation");

            List<Sector> sectorList = businessService.getAllSectorsWithAvg();
            model.addAttribute("sectors", sectorList);
            return "sector";
        } else return "inlogViewEmployee";
    }


}
