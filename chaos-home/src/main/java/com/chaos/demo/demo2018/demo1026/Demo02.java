package com.chaos.demo.demo2018.demo1026;

public class Demo02 {
    public static void main(String[] args){
        int i = trailingZeroes(10L);
        System.out.println(i);
    }
    public static  int trailingZeroes(Long n) {
        Long multy=n;
        Integer result=0;
        while(n>1){
            multy =multy*(n-1);
            n--;
        }
        System.out.println(multy);
        while(multy%10==0){
            result+=1;
            multy /=10;
        }
        return result;
    }
}
