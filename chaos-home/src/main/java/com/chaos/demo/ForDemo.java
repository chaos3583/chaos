package com.chaos.demo;

import com.chaos.date.DateUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: chaos
 * * @description: 几种循环效率比较
 * * @author: liaopeng
 * * @create: 2019-09-28 16:45
 **/
public class ForDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <10000000; i++) {
            list.add(i);
        }
        forMethod1(list);
    }
    public static void forMethod1(List<Integer> list){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");//设置日期格式
        System.out.println("开始时间："+ df.format(new Date()));
        int m=0;
        for (Integer i : list) {
            m =m+i;
        }
        System.out.println("结束时间时间："+df.format(new Date()));
    }

}
