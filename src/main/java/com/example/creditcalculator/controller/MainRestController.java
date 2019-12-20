package com.example.creditcalculator.controller;

import com.example.creditcalculator.entity.CreditInfo;
import com.example.creditcalculator.entity.Payment;
import com.example.creditcalculator.entity.ResponseForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.valueOf;

@RestController
public class MainRestController {
    @Value("${application.interest-rate}")
    private String interestRate;
    private DecimalFormat df = new DecimalFormat("#.##");

    @PostMapping("getPaymentList")
    public ResponseForm getPaymentList(@RequestBody CreditInfo creditInfo) {
        ResponseForm<List<Payment>> responseForm = new ResponseForm<>();
//        try {
            responseForm.setSuccess(true);
            responseForm.setObject(getPayment(creditInfo));
//
//        } catch (Exception e) {
//            responseForm.setSuccess(false);
//            responseForm.setData(e.toString());
//        }

        return responseForm;
    }

    private List<Payment> getPayment(CreditInfo creditInfo){
        List<Payment> paymentList = new ArrayList<>();

        double X = getPrincipalPayment(creditInfo);
        double S = valueOf(creditInfo.getSum());
        double P = Double.parseDouble(interestRate) / 1200;

        for (int i = 0; i < creditInfo.getTerm(); i++) {
            Payment payment = new Payment();

            payment.setNumber(i + 1);
            payment.setDate(LocalDate.now().plusMonths(i));
            payment.setPrincipalPayment(df.format(X));
            double Sn = S - (i) * X;
            payment.setBalanceOfPrincipal(df.format(Sn));
            double Pn = Sn * P / 12;
            payment.setInterestPayment(df.format(Pn));
            payment.setTotalPayment(df.format(X + Pn));

            paymentList.add(payment);

        }

        return paymentList;
    }

    private Double getPrincipalPayment(CreditInfo creditInfo) {
        double S = valueOf(creditInfo.getSum());
        double N = valueOf(creditInfo.getTerm());
        double P = Double.parseDouble(interestRate) / 1200;
//        double result = S * (P / (1 - Math.pow((1 + P), -N)));

        return S * (P + (P / (Math.pow((1 + P), N) - 1)));
    }

}
