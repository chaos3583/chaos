package com.chaos.demo.demo2018.demo1116;

/**
 * @program: demo
 * @description: 斐波那契数列:0,1,1,2,3,5,8,13...;当前数是它前两个数之和
 * 递推公式：f(n) = f(n-1)+f(n-2),n从0开始
 * @author: 廖鹏
 * @create: 2018-11-16
 **/
public class FibonacciSequence {

    public static void main(String[] args){
        int f = fibonacci2(5);
        System.out.println(f);
    }

    /**
     * 方式一：递归，有很多重复计算，时间复杂度：O(2^n)
     * @param n
     * @return
     */
    public static int fibonacci(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }

    /**
     * 方式二：循环，时间复杂度:O(n)
     * @param n
     * @return
     */
    public static int fibonacci2(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        int min =0;
        int max = 1;
        int i=2;
        int result=1;
        while (i<=n){
            result = min+max;
            min = max;
            max = result;
            i++;
        }
        return result;
    }
}
