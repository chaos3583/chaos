package com.chaos.demo.demo2018.demo1115;

/**
 * @program: demo
 * @description: 快速排序算法
 * @author: 廖鹏
 * @create: 2018-11-21
 **/
public class QuickSort {

    public static void main(String[] args){
        int[] a = {6,4,7,2,8,10,5};
        quickSort(a,0,a.length-1);
        for (int i = 0; i < a.length; i++) {
            int i1 = a[i];
            System.out.println(i1);
        }
    }
    /**
     * 快速排序
     * @param a
     * @param p
     * @param q
     */
    public static void quickSort(int[] a,int p,int q){
        if(p>=q){
            return;
        }
        //寻找分区点，并将参照值放到分区点上,保证分区点左边小于分区点，右边大于分区点
        int r = partition(a, p, q);
        //对分区点左右的数组分别单独再次进行快排
        quickSort(a,p,r-1);
        quickSort(a,r+1,q);
    }

    /**
     * 寻找分区点
     * @param a
     * @param p
     * @param q
     * @return
     */
    public static int partition(int[] a,int p,int q){
        int i=p;
        int key =a[q];
        //寻找分区点，其实就是找到参照值key应该在哪个位置
        for(int j=p;j<q;j++){
            if(a[j]<key){
                int temp = a[j];
                a[j]=a[i];
                a[i]=temp;
                i++;
            }
        }
        //上面的循环已经找到分区点，此处交换是把参照值放到分区点上
        int temp = a[q];
        a[q]=a[i];
        a[i]=temp;
        return i;
    }
}
