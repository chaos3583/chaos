package com.chaos.demo;

import java.util.Scanner;

/**
 * @program: chaos
 * * @description: 价值一个亿的AI核心代码
 * * @author: liaopeng
 * * @create: 2019-10-10 14:57
 **/
public class AiDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while(true){
            str=scanner.next();
            str=str.replace("吗","");
            str=str.replace("？","");
            str=str.replace("你","我");
            str=str.replace("说一句试试","不说");
            System.out.println(str);
        }
    }
}
