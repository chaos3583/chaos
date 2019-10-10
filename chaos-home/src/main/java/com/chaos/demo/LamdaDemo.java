package com.chaos.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: chaos
 * * @description: lamda表达式通过stream流的规约操作避免lamda内部修改外部变量报错
 * * @author: liaopeng
 * * @create: 2019-09-28 17:32
 **/
public class LamdaDemo {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        Integer sum=0;
//        sum = list.stream().reduce(0,(a,b)->a+b);
        /*list.forEach(s -> {
            sum=sum+s;
        });*/

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<List<String>> list3 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list2.add("3");
        list2.add("4");
        list3.add(list1);
        list3.add(list2);
        list3.forEach(l->{
            System.out.println("前："+l);
        });
        List<String> list4 = list3.stream().flatMap(l -> l.stream()).collect(Collectors.toList());

        list4.forEach(l->{
            System.out.println("后:"+l);
        });
    }
}
