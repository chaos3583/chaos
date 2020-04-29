package com.chaos.demo.demo2018.demo1108;

/**
 * @program: demo
 * @description: 递归调用demo
 * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
 * @author: 廖鹏
 * @create: 2018-11-08
 **/
public class RecursionDemo {

    public static void main(String[] args){
        int function = function(8);
        System.out.println(function);
    }

    public static int function(int n){
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        return function(n-1)+function(n-2);
    }
}
