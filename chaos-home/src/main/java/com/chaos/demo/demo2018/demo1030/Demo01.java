package com.chaos.demo.demo2018.demo1030;

import com.chaos.demo2018.demo1030.MyLinkedList.Node;

/**
 * @program: demo
 * @description: 测试单向链表
 * @author: 廖鹏
 * @create: 2018-11-01
 **/
public class Demo01 {
    public static void main(String[] args){
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.insertHead(2);
        linkedList.insertHead(3);
        linkedList.insertHead(4);
        linkedList.insertHead(5);
//        linkedList.insertLast(3);
//        linkedList.insertLast(2);
//        linkedList.insertLast(4);
//        Node byValue = linkedList.findByValue(3);
//        System.out.println(byValue.getData());
//        linkedList.remove(3);
        Node result = linkedList.revert2();
        linkedList.printAll();
    }
}
