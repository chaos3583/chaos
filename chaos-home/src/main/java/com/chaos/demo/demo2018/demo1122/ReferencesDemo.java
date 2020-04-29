package com.chaos.demo.demo2018.demo1122;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @program: demo
 * @description: java的四种引用，强引用、软引用、弱引用、虚引用
 * @author: 廖鹏
 * @create: 2018-11-22
 **/
public class ReferencesDemo {
    public static void main(String[] args){
        //强引用，只要引用还在，垃圾回收器就不会回收
        People strongPe= new People("chaos",20);
        System.out.println("强引用："+strongPe.getName());

        //软引用，可用但是非必须，在内存泄漏前垃圾回收器会进行回收
        SoftReference<People> softPe = new SoftReference<People>(new People("chaos",20));
        System.out.println("软引用回收前："+softPe.get());//.get()方法获取的是people对象
        System.gc();//通知垃圾回收器回收资源
        System.out.println("软引用回收后："+softPe.get());//此处未被回收

        //弱引用，非必需，比软引用更弱一些，不管内存您是否已满，垃圾回收器都会进行回收
        WeakReference<People> weakPe = new WeakReference<People>(new People("chaos",20));
        System.out.println("弱引用回收前："+weakPe.get());
        System.gc();//通知垃圾回收器回收资源
        System.out.println("弱引用回收后："+weakPe.get());//此处被回收，get()方法返回null

        //将上述代码修改一下，弱引用关联强引用，则垃圾回收不会读该弱引用进行回收，软引用也是如此
        WeakReference<People> weakRelateStrong = new WeakReference<People>(strongPe);//关联强引用
        System.out.println("弱引用关联强引用回收前："+weakRelateStrong.get());
        System.gc();//通知垃圾回收器回收资源
        System.out.println("弱引用关联强引用回收后："+weakRelateStrong.get());//此时垃圾回收并没有回收该弱引用

        //虚引用，必须和引用队列关联使用，可以通过判断队列中是否有虚引用来判断被虚引用引用的对象是否要被回收了
        ReferenceQueue<People> queue = new ReferenceQueue<People>();
        PhantomReference<People> phantomPe = new PhantomReference<People>(new People("chaos",20),queue);
        System.out.println("虚引用回收前："+phantomPe.get());//得到的永远是null
    }


}
