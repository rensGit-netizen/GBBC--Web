package com.gbbc.webapplication.services;

import com.gbbc.webapplication.beans.Employee;
import com.gbbc.webapplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class EmployeeLogInService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeLogInService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


//    public String loginCheck(Customer fieldcustomer, Model model){
//        System.out.println("In employeeLogin");
//    }


    public String getViewForEmployee(HttpSession session, Employee checkEmployee) {
        System.out.println(checkEmployee.getJob_title());
        if (checkEmployee.getJob_title().equalsIgnoreCase("bankmedewerker")) {
            session.setAttribute("userSession", checkEmployee);
            session.setAttribute("isLoggedIn", true);
            return "redirect:/terminal-requests";
        } else if (checkEmployee.getJob_title().equalsIgnoreCase("hoofdmkb")) {
            session.setAttribute("userSession", checkEmployee);
            session.setAttribute("isLoggedIn", true);
            return "redirect:/smes-customers";
        } else {
            session.setAttribute("userSession", checkEmployee);
            session.setAttribute("isLoggedIn", true);
            return "redirect:/private-banking-customers";
        }
    }


    public Employee getEmployeeCheck(Employee fieldEmployee) {
        Employee checkEmployee = employeeRepository.searchByEmployeeUserName(fieldEmployee.getUserName());
        return checkEmployee;
    }
}




