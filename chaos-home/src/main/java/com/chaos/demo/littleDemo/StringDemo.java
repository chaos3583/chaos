package com.chaos.demo.littleDemo;

public class StringDemo {
    public static void main(String[] args) {
        String a = "hello";
        String intern = a.intern();
        System.out.println("intern:"+intern);
    }
}
