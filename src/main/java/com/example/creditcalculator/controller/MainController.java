package com.example.creditcalculator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @Value("${application.interest-rate}")
    private String interestRate;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        request.setAttribute("interestRate", interestRate);
        return "credit-calculator";
    }



}
