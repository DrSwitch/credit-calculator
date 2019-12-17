package com.example.creditcalculator.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class CreditInfo {
    @Max(5000000)
    @Min(100000)
    private Integer sum;
    @Max(60)
    @Min(12)
    private Integer term;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "CreditInfo{" +
                "sum=" + sum +
                ", term=" + term +
                '}';
    }
}
