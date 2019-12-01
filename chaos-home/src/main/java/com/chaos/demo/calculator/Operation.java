package com.chaos.demo.calculator;

import java.math.BigDecimal;

public class Operation {
    public BigDecimal A;

    public BigDecimal B;

    public BigDecimal getA() {
        return A;
    }

    public void setA(BigDecimal a) {
        A = a;
    }

    public BigDecimal getB() {
        return B;
    }

    public void setB(BigDecimal b) {
        B = b;
    }

    public BigDecimal getResult(){
        return BigDecimal.ZERO;
    }
}

class OperationAdd extends Operation{
    @Override
    public BigDecimal getResult() {
        return A.add(B);
    }
}

class OperationSub extends Operation{
    @Override
    public BigDecimal getResult() {
        return A.subtract(B);
    }
}

class OperationMul extends Operation{
    @Override
    public BigDecimal getResult() {
        return A.multiply(B);
    }
}

class OperationDiv extends Operation{
    @Override
    public BigDecimal getResult() {
        return A.divide(B);
    }
}
