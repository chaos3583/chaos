package com.chaos.demo;

public class StringDemo {
    public static void main(String[] args) {
        String a = "hello";
        String intern = a.intern();
        System.out.println("intern:"+intern);
    }
}
