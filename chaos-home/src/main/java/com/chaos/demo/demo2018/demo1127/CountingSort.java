package com.chaos.demo.demo2018.demo1127;

/**
 * @program: demo
 * @description: 计数排序
 * @author: 廖鹏
 * @create: 2018-11-27
 **/
public class CountingSort {
    public static void main(String[] args){
        int[] a = {2,3,2,0};
        countintSort(a,a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void countintSort(int[] a,int n){
        if(n<=1){
            return;
        }
        //找出a中的最大值
        int max=a[0];
        for (int i = 0; i < n; i++) {
            if(max<a[i]){
                max=a[i];
            }
        }
        //根据上一步找到的max创建一个计数数组c，长度为max+1
        int[] c = new int[max+1];
        //给c中的元素初始化值0
        for (int i = 0; i < c.length; i++) {
            c[i]=0;
        }
        for (int i = 0; i <n; i++) {
            c[a[i]] ++;
        }
        //c的下标就是a中的元素，遍历c累加
        for (int i = 1; i <= max; i++) {
           c[i] = c[i-1]+c[i];
        }
        //定义临时的排序数组r
        int r[] = new int[n];
        for (int i = 0; i <n; i++) {
            int index = c[a[i]]-1;
            r[index] = a[i];
            c[a[i]]--;
        }
        //将r元素拷贝到a中
        for (int i = 0; i < n; i++) {
            a[i]= r[i];
        }
    }
}
