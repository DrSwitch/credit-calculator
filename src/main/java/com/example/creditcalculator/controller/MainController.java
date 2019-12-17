package com.example.creditcalculator.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${application.interest-rate}")
    private String interestRate;

    @GetMapping("/")
    public String index() {
        System.out.println("interestRate = " + interestRate);

        return "credit-calculator";
    }



}
