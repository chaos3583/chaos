package com.chaos.demo.demo2018.demo1115;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description: 冒泡排序
 * @author: 廖鹏
 * @create: 2018-11-15
 **/
public class BubbleSort {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(6);
        list.add(1);
        list.add(36);
        list.add(21);
        list.add(10);
        list.add(5);
        list.add(8);

        bubbleSort(list,list.size());

        for (Integer integer : list) {
            System.out.println(integer+"  ");
        }
    }

    public static void bubbleSort(List<Integer> list ,Integer size){
        if(!CollectionUtils.isEmpty(list)){
            for (int i = 0; i < size; i++) {
                 Integer m = list.get(i);
                for (int j = i+1; j < size; j++) {
                    Integer n=list.get(j);
                    if(m>n){
                        Integer temp = m;
                        m=n;
                        n=temp;
                    }
                    list.set(j,n);
                }
              list.set(i,m);
            }
        }
    }
}
