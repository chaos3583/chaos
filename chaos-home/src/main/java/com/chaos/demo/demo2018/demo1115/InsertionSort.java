package com.chaos.demo.demo2018.demo1115;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description: 插入排序
 * @author: 廖鹏
 * @create: 2018-11-15
 **/
public class InsertionSort {
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
        insertionSort(list,list.size());
        for (Integer integer : list) {
            System.out.println(integer+"  ");
        }
    }

    public static void insertionSort(List<Integer> list,Integer size){
        if(!CollectionUtils.isEmpty(list)){
            for (int i = 0; i < size; i++) {
                Integer m = list.get(i);
                int j=i-1;
                for (; j >=0; j--) {
                     if(m<list.get(j)){
                         list.set(j+1,list.get(j));
                     }else {
                         break;
                     }
                }
                list.set(j+1,m);
            }
        }
    }
}
