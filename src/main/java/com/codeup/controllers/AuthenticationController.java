package com.codeup.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "views/login";
    }

    @GetMapping("/logout")
    @ResponseBody
    public String showLogoutForm() {
        return "You have been logged out!";
    }
}



