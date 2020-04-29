package com.chaos.demo.demo2018.demo1101;


/**
 * @program: demo
 * @description: 测试LinkedListStack栈
 * @author: 廖鹏
 * @create: 2018-10-31
 **/
public class Demo01 {
    public static void main(String[] args){
        LinkedListStack stack = new LinkedListStack();
        stack.pushStack("a");
        stack.pushStack("b");
        stack.pushStack("c");
        stack.pushStack("d");
//        stack.outStack();
//        stack.printAll();
        System.out.println(stack.getTopStack());
    }
}
