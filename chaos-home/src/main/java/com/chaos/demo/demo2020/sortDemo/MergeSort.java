package com.chaos.demo.demo2020.sortDemo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-05-11 15:21
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] a = {6,4,7,2,8,10,5};
        mergeSort(a, 0, a.length - 1);
        for (int i = 0; i<a.length; i++) {
            int i1 = a[i];
            System.out.println(i1);
        }
    }

    public static void mergeSort(int[] a,int p,int q){
        if (p>=q) return ;
        int r = p + (q-p)/2;
        mergeSort(a,p,r);
        mergeSort(a,r+1,q);
        merge(a,p,r,q);
    }

    public static void merge(int[] a,int p,int r,int q){
        int i = p;
        int j = r+1;
        int [] temp =new int[q-p+1];
        int m=0;
        while(i<=r && j<=q){
            if (a[i]<=a[j]){
                temp[m++]=a[i++];
            }else {
                temp[m++]=a[j++];
            }
        }
        while (i<=r){
            temp[m++]=a[i++];
        }
        while (j<=q){
            temp[m++]=a[j++];
        }

        for (int k = 0; k <= q-p; k++) {
            a[p+k]=temp[k];
        }
    }
}
