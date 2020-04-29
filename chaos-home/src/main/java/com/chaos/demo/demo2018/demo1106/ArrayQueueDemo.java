package com.chaos.demo.demo2018.demo1106;

/**
 * @program: demo
 * @description: 测试数组实现的队列
 * @author: 廖鹏
 * @create: 2018-11-06
 **/
public class ArrayQueueDemo {
    public static void main(String[] args){
        ArrayQueue queue = new ArrayQueue(6);
        queue.inQueue("a");
        queue.inQueue("b");
        queue.inQueue("c");
        queue.inQueue("d");
        queue.outQueue();
        queue.printAll();

    }
}
