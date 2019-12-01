package com.chaos.demo.strategy;

import java.math.BigDecimal;

/**
 * 现金抽象类
 */
abstract class CashSupper {

    public abstract BigDecimal getResult(BigDecimal money);
}

/**
 * 正常收费
 */
class CashNormal extends CashSupper{
    @Override
    public BigDecimal getResult(BigDecimal money) {
        return money;
    }
}

/**
 *打折子类，继承现金抽象类
 */
class CashRebate extends CashSupper{
    BigDecimal moneyRebate = BigDecimal.ONE;
    public CashRebate(BigDecimal moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public BigDecimal getResult(BigDecimal money) {
        return money.multiply(moneyRebate);
    }
}

/**
 * 满减
 */
class CashReturn extends CashSupper{

    BigDecimal fullMoney=BigDecimal.ZERO;
    BigDecimal returnMoney = BigDecimal.ZERO;
    public CashReturn(BigDecimal fullMoney,BigDecimal returnMoney) {
        this.fullMoney=fullMoney;
        this.returnMoney=returnMoney;
    }

    @Override
    public BigDecimal getResult(BigDecimal money) {
        BigDecimal totalReturn = money.divide(fullMoney, BigDecimal.ROUND_DOWN).multiply(returnMoney);
        return money .subtract(totalReturn);
    }
}
