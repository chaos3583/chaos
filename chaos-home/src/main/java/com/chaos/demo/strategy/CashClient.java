package com.chaos.demo.strategy;

import java.math.BigDecimal;

public class CashClient {
    public static void main(String[] args) {
        BigDecimal cashResult = getCashResult("3", new BigDecimal("120"));
        System.out.println("打折结果为："+cashResult);
    }

    public static BigDecimal getCashResult(String strategy,BigDecimal money){
        Context context=new Context("3");
        return context.getInterface(money);
    }
}
