package com.chaos.demo.demo2018.demo1101;

/**
 * @program: demo
 * @description: 基于链表的栈
 * @author: 廖鹏
 * @create: 2018-11-01
 **/
public class LinkedListStack {
    private  Node top;
    private int size;

    public LinkedListStack() {
        this.top = null;
        this.size = 0;
    }

    /**
     * 入栈
     * @param value
     */
    public void pushStack(String value){
        Node node = this.top;
        Node newNode = new  Node(value,null);
        if(node==null){
            this.top=newNode;
        }else{
            newNode.next=this.top;
            this.top=newNode;
        }
        size++;
    }

    /**
     * 出栈
     * @return
     */
    public String outStack(){
        if(this.top==null){
            return null;
        }
        Node outNode = this.top;
        this.top = this.top.next;
        size--;
        return outNode.getData();
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public String getTopStack(){
        if(this.top==null){
            return "is empty";
        }else {
            return this.top.getData();
        }
    }

    /**
     * 打印栈中的所有数据
     */
    public void printAll(){
        if(this.top==null){
            System.out.println(" ");
        }else{
            Node currentNode = this.top;
            while (currentNode!=null){
                System.out.println(currentNode.getData()+" ");
                currentNode=currentNode.next;
            }
        }
    }

    /**
     * 清空栈
     */
    public void clearStack(){
        this.top=null;
        this.size=0;
    }

    private class Node{
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }
        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
