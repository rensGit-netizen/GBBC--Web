package com.gbbc.webapplication.controllers;

import com.gbbc.webapplication.beans.Employee;
import com.gbbc.webapplication.repository.EmployeeRepository;
import com.gbbc.webapplication.services.EmployeeLogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
/**
 * Controller for employeeLogin
 *
 * @author R. Portzgen
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeLogInService employeeLogInService;



    @PostMapping("/employeeControle")
    public String employeeLogin(@ModelAttribute("employee") Employee fieldEmployee, Model model, HttpSession employeeSession) {
        Employee checkEmployee = employeeLogInService.getEmployeeCheck(fieldEmployee);
        if (checkEmployee == null) {
            model.addAttribute("invalidLogin", "Controleer je wachtwoord en gebruikersnaam.");
            model.addAttribute("employee", new Employee());
            return "inlogViewEmployee";
        } else if (!checkEmployee.getPassword().equals(fieldEmployee.getPassword())) {
            model.addAttribute("invalidLogin", "Controleer je wachtwoord en gebruikersnaam.");
            model.addAttribute("employee", new Employee());
            return "inlogViewEmployee";
        } else {
            employeeSession.setAttribute("isLoggedIn", true);
            model.addAttribute("isLoggedIn", (employeeSession.getAttribute("isLoggedIn") != null));
            return employeeLogInService.getViewForEmployee(employeeSession, checkEmployee);
        }
    }


}

