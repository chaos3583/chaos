package com.chaos.demo.demo2018.demo0821;

/**
 * 判断一个数是否是素数
 */
public class Demo1 {
    /**
     * 方法一：遍历所有小于N的数
     * @param N
     * @return
     */
        public static Boolean isPrime1(int N){
        if(N<2){
            return false;
        }
        Boolean flag=true;
        for (int i=2;i<N;i++){
            if(N%i==0){
                flag= false;
                break;
            }
        }
        return flag;
    }

    /**
     * 方法二：去掉偶数,速度提高了一倍
     * @param N
     * @return
     */
    public static Boolean isPrime2(int N){
        if(N<2){
            return false;
        }
        if(N==2){
            return true;
        }
        Boolean flag=true;
        for (int i=3;i<N;i+=2){
            if(N%i==0){
                flag= false;
                break;
            }
        }
        return flag;
    }
    /**方法三：
     * 一个数除以比它一半还要大的数一定是除不尽的
     * @param N
     * @return
     */
    public static  Boolean isPrime3(int N){
        if (N<2){
            return false;
        }
        for (int i=2;i<N/2;i++){
            if(N%i==0){
                return  false;
            }
        }
        return true;
    }

    /**
     * 方法四：进一步减少循环次数
     * 定理：如果n不是素数，则n有满足1<d<=sqrt(n)的一个因子d（sqrt（）为取平方根）
     * @param N
     * @return
     */
    public static  Boolean isPrime4(int N){
        if (N<2){
            return false;
        }
        for (int i=2;i*i<N;i++){
            if(N%i==0){
                return  false;
            }
        }
        return true;
    }

    public  static void main(String[] args){
//        Boolean flag= isPrime1(97);
//        Boolean flag=isPrime2(95);
        Boolean prime3 = isPrime3(76);
        Boolean prime4 = isPrime4(67);
        double sqrt = Math.sqrt(13);//取平方根
        System.out.println(prime4);
    }
}
