package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.TerminalRequest;
import com.gbbc.webapplication.services.sessionServices.CustomerSessionService;
import com.gbbc.webapplication.services.interfaces.TerminalService;
import com.gbbc.webapplication.viewmodel.PinTerminalStatus;
import com.gbbc.webapplication.services.sessionServices.EmployeeSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * Controller for bankEmployeeView, controlCodeDisplay
 *
 * @author R. Portzgen
 */
@Controller
public class TerminalRequestController {
    @Autowired
    public TerminalService terminalService;

    @Autowired
    CustomerSessionService customerSessionService;

    @Autowired
    EmployeeSessionService employeeSessionService;

    /**
     * Medewerker:  Pagina overzicht Terminals
     *
     * @return bankEmployeeView.jsp
     */
    @GetMapping("/terminal-requests")
    public String goToConnectTerminal(Model model, HttpSession employeeSession) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("in Terminal Request Controller");
            String employeeName = employeeSessionService.getLoggedInUser().getFirst_name();
            List<TerminalRequest> allRequests = terminalService.getAllRequests();
            model.addAttribute("requests", allRequests);
            model.addAttribute("employee", employeeName);
            return "bankEmployeeView";
        } else return "inlogViewEmployee";
    }


    @PostMapping("/controlCode")
    public String handleTerminalRequest(@ModelAttribute("pinTerminalStatus")PinTerminalStatus terminalStatus, Model model, HttpSession employeeSession) {
        if (employeeSessionService.isLoggedIn()) {
            System.out.println("in control code");
            String employeeName = employeeSessionService.getLoggedInUser().getFirst_name();
            //Opslaan van wijzigingen in Request
            terminalService.handleRequest(terminalStatus);
            //Meegeven terminalStatus aan controlCode view
            model.addAttribute("terminalstatus", terminalStatus);
            model.addAttribute("employee", employeeName);
            return "controlCodeDisplay";
        } else return "inlogViewEmployee";

    }


    @PostMapping("/request-terminal")
    public String customerTerminalRequest(@ModelAttribute("pinTerminalStatus")PinTerminalStatus terminalStatus, Model model, HttpSession session){
        if (session.getAttribute("userSession")!=null) {
            System.out.println("in control code");
            String aanvrager = customerSessionService.getLoggedInUser().getFirst_name();
            if(terminalService.checkForRequest(terminalStatus)){
                model.addAttribute("infopanel", "Er is al een aanvraag voor een pinautomaat verstuurd. Check uw inbox!");
            return "accountOverviewView";
            }
            PinTerminalStatus newStatus = terminalService.generateRequest(terminalStatus);
            model.addAttribute("terminalstatus", newStatus);
            return "requestTerminal";
        } else return "inlogView";
    }

}



