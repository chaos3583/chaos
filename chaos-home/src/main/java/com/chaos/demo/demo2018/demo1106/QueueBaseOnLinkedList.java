package com.chaos.demo.demo2018.demo1106;

/**
 * @program: demo
 * @description: 基于链表的队列
 * @author: 廖鹏
 * @create: 2018-11-08
 **/
public class QueueBaseOnLinkedList {

    private Node head;
    private Node tail;

    public QueueBaseOnLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * 入队
     * @param value
     * @return
     */
    public void inQueue(String value){
        Node node = new Node(value,null);
        if(head==null){
            //说明队列为空
            tail=node;
            head=node;
            tail.next=null;
        }else{
            tail.next=node;
            tail =tail.next;
        }
    }

    /**
     * 出队
     */
    public void outQueue(){
        if(head==null){
            return;
        }
        head=head.next;
    }


    public void printAll(){
        if(head==null){
            System.out.println("is empty");
            return;
        }
        Node tempNode = head;
        while (tempNode!=null){
            System.out.println(tempNode.getData());
            tempNode = tempNode.next;
        }
    }

    public class Node{
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
