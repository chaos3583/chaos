package com.chaos.demo.demo2018.demo1106;

/**
 * @program: demo
 * @description: 数组实现的循环队列
 * @author: 廖鹏
 * @create: 2018-11-06
 **/
public class CircularArrayQueue {
    private String[] items;
    private int head;
    private int tail;
    private int n;

    public CircularArrayQueue(int size){
        items=new String[size];
        this.n=size;
        head=0;
        tail=0;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public Boolean inQueue(String item){
        if((tail+1)% n==head){
            // 说明队列已满
            return false;
        }
        items[tail] = item;
        tail = (tail+1)%n;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String outQueue(){
        if(tail==head){
            return null;
        }
        String temp = items[head];
        items[head] = null;
        head = (head+1)%n;
        return temp;
    }

    public void printAll(){
        if(tail==head){
            System.out.println("is empty");
            return ;
        }
        for (int i = head; i%n!=tail; i=(i+1)%n) {
            String item = items[i];
            System.out.println(item);
        }
    }
}
