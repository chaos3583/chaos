package com.chaos.demo.demo2018.demo1120;

/**
 * @program: demo
 * @description: 二分查找变体2，查找最后一个重复的元素
 * @author: 廖鹏
 * @create: 2018-11-29
 **/
public class FindLastRepeated {
    public static void main(String[] args){
        int[] a = {2,3,4,5,6,6,7};
        int result = findLastRepeated(a,a.length,6);
        System.out.println(result);
    }

    public static int findLastRepeated(int[] a,int n,int value){
        int start=0;
        int end = n-1;
        while (start<=end){
            int mid = start + ((end-start)>>1);//等效于start+(end-start)/2
            if(value<a[mid]){
                end = mid-1;
            }else if(value>a[mid]){
                start = mid+1;
            }else{//找到了value对应的元素
                if(mid==0 || a[mid+1]!=value){
                    return mid;
                }else{
                    //当前找到的元素不是最后一个，还要继续进行二分
                    start = mid +1;
                }
            }
        }
        return -1;
    }
}
