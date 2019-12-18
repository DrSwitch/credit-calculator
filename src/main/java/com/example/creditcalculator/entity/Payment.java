package com.example.creditcalculator.entity;

import java.time.LocalDate;

public class Payment {
    private int number;
    private LocalDate date;
    private String principalPayment;
    private String interestPayment;
    private String balanceOfPrincipal;
    private String totalPayment;

    @Override
    public String toString() {
        return "Payment{" +
                "number=" + number +
                ", date=" + date +
                ", principalPayment='" + principalPayment + '\'' +
                ", interestPayment='" + interestPayment + '\'' +
                ", balanceOfPrincipal='" + balanceOfPrincipal + '\'' +
                ", totalPayment='" + totalPayment + '\'' +
                '}';
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPrincipalPayment() {
        return principalPayment;
    }

    public void setPrincipalPayment(String principalPayment) {
        this.principalPayment = principalPayment;
    }

    public String getInterestPayment() {
        return interestPayment;
    }

    public void setInterestPayment(String interestPayment) {
        this.interestPayment = interestPayment;
    }

    public String getBalanceOfPrincipal() {
        return balanceOfPrincipal;
    }

    public void setBalanceOfPrincipal(String balanceOfPrincipal) {
        this.balanceOfPrincipal = balanceOfPrincipal;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }
}
