package com.chaos.demo.strategy;

import java.math.BigDecimal;

public class Context {
    CashSupper cashSupper;
    public Context(String strategyType) {
        switch (strategyType){
            case "1":
                cashSupper=new CashNormal();
                break;
            case "2":
                cashSupper=new CashRebate(new BigDecimal("0.5"));
                break;
            case "3":
                cashSupper=new CashReturn(new BigDecimal("100"),new BigDecimal("50"));
                break;
        }
    }
    public BigDecimal getInterface(BigDecimal money){
        BigDecimal result = cashSupper.getResult(money);
        return result;
    }
}
