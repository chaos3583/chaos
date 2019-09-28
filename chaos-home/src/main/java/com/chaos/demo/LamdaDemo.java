package com.chaos.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: chaos
 * * @description: lamda表达式通过stream流的规约操作避免lamda内部修改外部变量报错
 * * @author: liaopeng
 * * @create: 2019-09-28 17:32
 **/
public class LamdaDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Integer sum=0;
        sum = list.stream().reduce(0,(a,b)->a+b);
        /*list.forEach(s -> {
            sum=sum+s;
        });*/

    }
}
