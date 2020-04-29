package com.chaos.demo.demo2018.demo1122;

/**
 * @program: demo
 * @description: 实体类
 * @author: 廖鹏
 * @create: 2018-11-22
 **/
public class People {
    private String name;
    private int age;
    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
            this.age = age;
        }
}
