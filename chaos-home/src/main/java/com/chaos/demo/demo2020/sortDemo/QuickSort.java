package com.chaos.demo.demo2020.sortDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-04-21 14:41
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {6,4,7,2,8,10,5};
        quickSort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++) {
            int i1 = a[i];
            System.out.println(i1);
        }
    }


    public static void quickSort(int [] a,int p,int q){
        if (p>=q){return;}
        int pivot = getPivot(a, p, q);
        quickSort(a,p,pivot-1);
        quickSort(a,pivot+1,q);
    }

    public static int getPivot(int [] a,int p,int q) {
        int i =p;
        int value = a[q];
        for (int j =p; j < q; j++) {
            if (a[j]<value){
                int temp = a[j];
                a[j] = value;
                a[i] = temp;
                i++;
            }
        }
        int temp = a[q];
        a[q] =a[i];
        a[i] = temp;
        return i;
    }
}
