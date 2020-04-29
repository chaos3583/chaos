package com.chaos.demo.demo2018.demo1120;

/**
 * @program: demo
 * @description: 二分查找变体3，查找第一个大于给定值的元素
 * @author: 廖鹏
 * @create: 2018-11-29
 **/
public class FindFirstGreater {
    public static void main(String[] args){
        int[] a = {1,2};
        int result = findFirstGreater(a,a.length,1);
        System.out.println(result);
    }

    public static int findFirstGreater(int[] a,int n,int value){
        if(n<=1){
            return -1;
        }
        int start =0;
        int end = n-1;
        while (start<=end){
            int mid = start + ((end-start)>>1);
            if(value<a[mid]){
                end = mid-1;
            }else if(value>a[mid]){
                start = mid+1;
            }else {
                if(a[mid+1]>value){
                    return mid+1;
                }else{
                    end = mid+1;
                }
            }
        }
        return -1;
    }
}
