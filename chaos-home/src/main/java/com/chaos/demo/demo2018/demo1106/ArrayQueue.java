package com.chaos.demo.demo2018.demo1106;

/**
 * @program: demo
 * @description: 数组实现顺序队列
 * @author: 廖鹏
 * @create: 2018-11-06
 **/
public class ArrayQueue {
    private String[] items;
    private int head;
    private int tail;
    private int n;

    public ArrayQueue(int n){
        items = new String[n];
        this.n =n;
        head =0;
        tail =0;
    }

    /**
     * 入队
     * @param item
     * @return
     */
    public Boolean inQueue(String item){
        if(tail ==n){
            //队列已满
            return false;
        }
        items[tail]=item; 
        tail++;
        return true;
    }

    /**
     * 出队
     * @return
     */
    public String outQueue(){
        if(head==tail){
            return null;
        }
        String temp = items[head];
        items[head]=null;

        head++;
        return temp;

    }

    /**
     * 打印所有元素
     */
    public void printAll(){
        if(head==tail){
           System.out.println("is empty");
        }
        System.out.println("头："+head);
        System.out.println("尾："+tail);
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            if(item!=null){
                System.out.println(item);
            }
        }
    }
}
