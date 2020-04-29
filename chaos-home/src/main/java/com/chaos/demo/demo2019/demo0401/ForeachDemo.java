package com.chaos.demo.demo2019.demo0401;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-04-01 14:13
 **/
public class ForeachDemo {
    public static void main(String[] args) {
//       Map<String,Object> treeMap = new TreeMap<>(new Comparator<String>() {
//           @Override
//           public int compare(String o1, String o2) {
//               return -1*o1.compareTo(o2);
//           }
//       });
//
//       treeMap.put("2","213");
//       treeMap.put("1",321);
//       treeMap.put("3",3453);
//       treeMap.forEach(new BiConsumer<String, Object>() {
//           @Override
//           public void accept(String s, Object o) {
//               System.out.println(s);
//           }
//       });

       List<String> list = new ArrayList<String>(){
           {
               this.add("123");
           }
       };
       list.forEach(s -> {
           System.out.println(s);
       });



    }

}
