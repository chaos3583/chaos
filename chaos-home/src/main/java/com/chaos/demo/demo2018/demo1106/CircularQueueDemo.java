package com.chaos.demo.demo2018.demo1106;

/**
 * @program: demo
 * @description: 测试数组实现的循环队列,循环队列始终会浪费一个数组空间
 * @author: 廖鹏
 * @create: 2018-11-08
 **/
public class CircularQueueDemo {
    public static void main(String[] args){
        CircularArrayQueue queue = new CircularArrayQueue(5);
        queue.inQueue("a");
        queue.inQueue("b");
        queue.inQueue("c");
        queue.inQueue("d");
        queue.outQueue();
        queue.outQueue();
        queue.outQueue();
        queue.outQueue();
        queue.inQueue("h");
        Boolean e = queue.inQueue("e");
        Boolean f = queue.inQueue("f");
        queue.printAll();
    }
}
