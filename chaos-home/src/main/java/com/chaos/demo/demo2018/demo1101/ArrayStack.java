package com.chaos.demo.demo2018.demo1101;

/**
 * @program: demo
 * @description: 用数组实现栈
 * @author: 廖鹏
 * @create: 2018-11-06
 **/
public class ArrayStack {
    private int count=0;
    private String[] items;
    private int size=0;

    public ArrayStack(int n) {
        items = new String[n];
        size=n;
    }

    /**
     * 入栈
     * @param item
     */
    public Boolean pushStack(String item){
        if(count==size){
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    /**
     * 出栈
     * @return
     */
    public String outStack(){
        if(count==0){
            return null;
        }
        String outString = items[count-1];
        count--;
        return outString;
    }

    /**
     * 打印栈中的所有元素
     */
    public void printAll(){
        if(count==0){
            System.out.println("is empty");
        }
        for (int i = 0; i < items.length; i++) {
            String item = items[i];
            System.out.println(item);
        }
    }
}
