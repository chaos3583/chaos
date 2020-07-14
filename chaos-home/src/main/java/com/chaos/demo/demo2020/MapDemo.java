package com.chaos.demo.demo2020;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: chaos
 * * @description:hashMap与LinkedHashMap顺序问题
 *      HashMap按照key值大小排序，因为散列表是基于数组的
 *      LinkedHashMap按照插入顺序排序，可以设置按照访问时间排序
 * * @author: liaopeng
 * * @create: 2020-05-14 15:28
 **/
public class MapDemo {

    public static void main(String[] args) {
//        Map<Integer,Integer> map = new HashMap<>();
//        Map<Integer,Integer> map = new LinkedHashMap<>();
        //16:初始大小，0.75:装载因子，true:表示按照访问时间排序
        Map<Integer,Integer> map = new LinkedHashMap<Integer, Integer>(16,0.75f,true);
        map.put(2,2);
        map.put(3,3);
        map.put(1,1);
        map.put(2,8);
        map.put(6,6);

        map.get(2);
        for (Integer integer : map.keySet()) {
            System.out.println(integer);
        }


    }

}
