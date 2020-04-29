package com.chaos.demo.demo2018.demo1115;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description: 选择排序
 * @author: 廖鹏
 * @create: 2018-11-15
 **/
public class SelectionSort {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(4);
        list.add(1);
        list.add(36);
        list.add(21);
        list.add(10);
        list.add(5);
        list.add(8);
        selectionSort(list,list.size());
        for (Integer integer : list) {
            System.out.println(integer+"  ");
        }
    }

    public static void selectionSort(List<Integer> list,Integer size){
        if(size<=1){
            return;
        }
        for (int i = 0; i <size; i++) {
            //找出最小值的索引
            int mIndex =i;
            for (int j = i; j <size; j++) {
                Integer value = list.get(j);
                 if(value<list.get(mIndex)){
                     mIndex=j;
                 }
            }
            //将最小值与与当前值进行交换
            if(i!=mIndex){
                Integer temp = list.get(mIndex);
                list.set(mIndex,list.get(i));
                list.set(i,temp);
            }
        }
    }
}
