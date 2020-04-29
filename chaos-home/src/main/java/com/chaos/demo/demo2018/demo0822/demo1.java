package com.chaos.demo.demo2018.demo0822;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap的put方法，当key已存在时，用新的v替换旧的v，然后返回旧的v
 */
public class demo1 {

    public static void main(String[] args){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("1", 1);
        Object put2 = params.put("1", 2);
        System.out.println(put2.toString());
    }
}
