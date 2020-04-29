package com.chaos.demo.demo2018.demo0821;

/**
 * 二分法查找
 */
public class Demo2 {
    public  static  int rank(int key,int[] a){
      int lo = 0;
      int hi = a.length-1;
      while (lo<hi){
          int mid = lo+(hi-lo)/2;
          if(key<a[mid]) {
              hi = mid - 1;
          }else if (key>a[mid]){
              lo=mid+1;
          }else{
              return mid;
          }
      }
      return -1;
    }
    public static void main(String[] args){
        int[] a = {1,2,3,4,5,6,9,7,8};
        int key=9;
        int rank = rank(key, a);
        System.out.println(rank);
    }

}
