package com.gbbc.webapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogOutController {

    @GetMapping("/logOut")
    public String logOut(HttpSession session){
        if (!(session.getAttribute("userSession") == null)){
            System.out.println("In logoutController. Iemand in session ");
            session.invalidate();
            return "homeView";
        } else {
            System.out.println("In session controller. None in session");
            return "homeView";
        }
    }
}
