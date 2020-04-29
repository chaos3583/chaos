package com.chaos.demo.demo2018.demo1120;


/**
 * @program: demo
 * @description: 简单的二分查找
 * 二分查找针对的是有序数据集合
 * @author: 廖鹏
 * @create: 2018-11-20
 **/
public class BinarySearch {

    public static void main(String[] args){
        int[] a = {2,3,4,5,6,7,8,9,10};
        int i = bSearch(a, 5);
//        int i = bSearch2(a, 0, a.length - 1, 5);
        System.out.println(i);
    }

    /**
     * 二分查找，循环遍历方式
     * 注意三点：
     * 1、while循环退出条件是start <= end 而不是start < end
     * 2、mid取值，这里用的是start+(end-start)/2而不是(start+end)/2,因为当数据很大的时候start+end可能会溢出
     * 3、循环中start和end的取值，是mid-1和mid+1而不是mid1
     * @param a
     * @param value
     */
    public static int bSearch(int[] a ,int value){
        if(a.length==0){
            return -1;
        }
        int start=0;
        int end=a.length-1;
        while (start<=end){
            int mid = start + (end-start)/2;
            if(a[mid]==value){
                return mid;
            }else if(a[mid]>value){
                end = mid-1;
            }else if(a[mid]<value){
                start=mid+1;
            }
        }
        return -1;
    }

    /**
     * 二分查找，递归方式
     * @param a
     * @param value
     * @return
     */
    public static int bSearch2(int[] a,int start,int end,int value){
        if(start>end){
            return -1;
        }
        int mid = start+(end-start)/2;
        if(a[mid]==value){
            return mid;
        }else if(a[mid]<value){
            return bSearch2(a,mid+1,end,value);
        }else{
            return bSearch2(a,start,mid-1,value);
        }
    }

}
