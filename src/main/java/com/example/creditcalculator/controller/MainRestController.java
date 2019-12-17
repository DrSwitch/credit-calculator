package com.example.creditcalculator.controller;

import com.example.creditcalculator.entity.CreditInfo;
import com.example.creditcalculator.entity.Payment;
import com.example.creditcalculator.entity.ResponseForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MainRestController {
    @Value("${application.interest-rate}")
    private String interestRate;

    @PostMapping("getPaymentList")
    public ResponseForm getPaymentList(@RequestBody CreditInfo creditInfo) {
        ResponseForm<List<Payment>> responseForm = new ResponseForm<>();
        responseForm.setSuccess(true);
        System.out.println(creditInfo);
        responseForm.setObject(getPayment());

        return responseForm;
    }

    private List<Payment> getPayment(){
        List<Payment> paymentList = new ArrayList<>();

        return paymentList;
    }

}
