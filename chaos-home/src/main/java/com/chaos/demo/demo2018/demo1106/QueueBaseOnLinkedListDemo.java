package com.chaos.demo.demo2018.demo1106;

/**
 * @program: demo
 * @description: 测试基于链表实现的队列demo
 * @author: 廖鹏
 * @create: 2018-11-08
 **/
public class QueueBaseOnLinkedListDemo {
    public static void main(String[] args){
        QueueBaseOnLinkedList queue = new QueueBaseOnLinkedList();
        queue.inQueue("a");
        queue.inQueue("b");
        queue.inQueue("c");
        queue.inQueue("d");
        queue.outQueue();
        queue.outQueue();
        queue.outQueue();
        queue.outQueue();
        queue.printAll();
    }
}
