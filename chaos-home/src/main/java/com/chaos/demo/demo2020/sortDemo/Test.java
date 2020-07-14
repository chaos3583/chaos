package com.chaos.demo.demo2020.sortDemo;

import java.util.*;

/**
 * @program: demo
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-04-21 10:30
 **/
public class Test {
    public static void main(String[] args) {
        int [] a = {2,3,1,66,5,3,7};
        BubbleSort.bubbleSort(a);
        printAll(a);

        Map<String,String> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        treeMap.put("2","2");
        treeMap.put("1","1");
        treeMap.put("3","3");
        treeMap.put("5","5");
        Map<String,String> hashMap = new HashMap<>();
        List<Integer> list = Arrays.asList(2,3,1,66,5,3,7);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
    }

    public static void printAll(int [] a){
        for (int i = 0; i < a.length; i++) {
            int m = a[i];
            System.out.println(m);
        }
    }
}
