package com.chaos.demo.demo2020.sortDemo;

/**
 * @program: demo
 * * @description:冒泡排序
 * * @author: liaopeng
 * * @create: 2020-04-21 10:28
 **/
public class BubbleSort {

    public static void bubbleSort(int[] a){
        int n = a.length;
        if (n<=0){return;}
        for (int i = 0; i < a.length; i++) {
            int m = a[i];
            for (int j = i+1; j < a.length; j++) {
                if (a[i]>=a[j]){
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}


