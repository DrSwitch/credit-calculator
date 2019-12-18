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
        try {
            responseForm.setSuccess(true);
            responseForm.setObject(getPayment(creditInfo));

        } catch (Exception e) {
            responseForm.setSuccess(false);
            responseForm.setData(e.toString());
        }

        return responseForm;
    }

    private List<Payment> getPayment(CreditInfo creditInfo){
        List<Payment> paymentList = new ArrayList<>();

        String principalPayment = getPrincipalPayment(creditInfo);
        for (int i = 0; i < creditInfo.getTerm(); i++) {
            Payment payment = new Payment();
            paymentList.add(payment);

            paymentList.get(i).setNumber(i+1);
            paymentList.get(i).setDate(LocalDate.now().plusMonths(i));
            paymentList.get(i).setPrincipalPayment(principalPayment);
            paymentList.get(i).setBalanceOfPrincipal(getBalanceOfPrincipal(creditInfo, paymentList));
            paymentList.get(i).setInterestPayment(getInterestPayment(paymentList));
            paymentList.get(i).setTotalPayment(getTotalPayment(paymentList));

        }

        return paymentList;
    }

    private String getPrincipalPayment(CreditInfo creditInfo) {
        double S = valueOf(creditInfo.getSum());
        double N = valueOf(creditInfo.getTerm());
        double P = Double.parseDouble(interestRate) / 1200;
//        double result = S * (P / (1 - Math.pow((1 + P), -N)));
        double result = S * (P + (P / (Math.pow((1 + P), N) - 1)));

        return df.format(result);
    }

    private String getBalanceOfPrincipal(CreditInfo creditInfo, List<Payment> paymentList) {
        double S = valueOf(creditInfo.getSum());
        for (int i = 0; i < paymentList.size() - 1; i++) {
            S = S - Double.parseDouble(paymentList.get(i).getPrincipalPayment())
                    + Double.parseDouble(paymentList.get(i).getInterestPayment());
        }

        return df.format(S);
    }

    private String getInterestPayment(List<Payment> paymentList) {
        double Sn = Double.parseDouble(paymentList.get(paymentList.size() - 1).getBalanceOfPrincipal());
        double P = Double.parseDouble(interestRate) / 1200;
        double result = Sn * P / 12;

        return df.format(result);
    }

    private String getTotalPayment(List<Payment> paymentList) {
        double balanceOfPrincipal = Double.parseDouble(paymentList.get(paymentList.size()-1).getBalanceOfPrincipal());
        double interestPayment = Double.parseDouble(paymentList.get(paymentList.size()-1).getInterestPayment());

        return df.format(balanceOfPrincipal + interestPayment);
    }

}
