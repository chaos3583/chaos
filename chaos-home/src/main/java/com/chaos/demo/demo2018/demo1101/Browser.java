package com.chaos.demo.demo2018.demo1101;

/**
 * @program: demo
 * @description: 利用栈来实现浏览器的回退和前进功能
 * @author: 廖鹏
 * @create: 2018-11-01
 **/
public class Browser {

    private LinkedListStack forwardBrowserStack;
    private LinkedListStack backBrowserStack;
    private String currentPage;

    public Browser() {
        this.forwardBrowserStack = new LinkedListStack();
        this.backBrowserStack = new LinkedListStack();
    }

    /**
     * 前进，从forwardBrowser栈中取出栈顶网址,
     * @return
     */
    public String forwardBrowser(){
        String forwardAddress = forwardBrowserStack.outStack();//forwardBrowserStack出栈
        backBrowserStack.pushStack(forwardAddress);//backBrowserStack入栈
        currentPage=forwardAddress;
        return forwardAddress;
    }

    /**
     * 回退，从backBrowser栈中取出栈顶的网址
     */
    public String backBrowser(){
        String outAddress = backBrowserStack.outStack();//出栈
        forwardBrowserStack.pushStack(outAddress);//将出栈的网址入栈到forwardBrowserStack的栈顶
        String backAddress = backBrowserStack.getTopStack();//取栈顶网址
        this.currentPage=backAddress;//当前页为backBrowserStack的栈顶网址
        return backAddress;
    }

    /**
     * 打开一个新的网址，将网址存到backBrowser
     * @param address
     */
    public void open(String address){
        backBrowserStack.pushStack(address);
        forwardBrowserStack.clearStack();
        currentPage=address;
    }

    /**
     * 打印当前所在的网址
     */
    public void printCurrentAddress(){
        System.out.println(currentPage);
    }
}
