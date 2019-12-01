package com.chaos.demo.calculator;

import java.math.BigDecimal;

public class CalculatorClient {
    public static void main(String[] args) {
        Operation operation = OperationFactory.createOperation("+");
        operation.setA(new BigDecimal("20"));
        operation.setB(new BigDecimal("30"));
        BigDecimal result = operation.getResult();
        System.out.println("结果为:"+result);
    }
}
