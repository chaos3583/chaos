package com.chaos.demo.demo2018.demo1101;

/**
 * @program: demo
 * @description: 测试浏览器的前进和回退功能
 * @author: 廖鹏
 * @create: 2018-11-01
 **/
public class Demo02 {
    public static void main(String[] args){
        Browser browser = new Browser();
        browser.open("www.baidu.com");
        browser.open("www.googel.cn");
        browser.open("www.china.cn");
        browser.open("www.github.cn");
        browser.open("www.csdn.com");
        browser.backBrowser();
        browser.backBrowser();
        browser.backBrowser();
        browser.backBrowser();
        browser.forwardBrowser();
        browser.forwardBrowser();
        browser.forwardBrowser();
        browser.forwardBrowser();
        browser.forwardBrowser();
        browser.printCurrentAddress();
    }
}
