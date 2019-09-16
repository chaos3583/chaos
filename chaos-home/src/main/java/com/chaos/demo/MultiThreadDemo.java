package com.chaos.demo;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2019-09-03 14:54
 **/
public class MultiThreadDemo {
    public static void main(String[] args) {
        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        //遍历线程，打印线程id和线程名称
        for (int i = 0; i < threadInfos.length; i++) {
            ThreadInfo threadInfo = threadInfos[i];
            System.out.println(threadInfo.getThreadId()+"---"+threadInfo.getThreadName());
        }
    }
    /**6---Monitor Ctrl-Break
     5---Attach Listener
     4---Signal Dispatcher  分发处理发送给jvm信号的线程
     3---Finalizer     调用对象finalize方法的线程
     2---Reference Handler  清楚Reference的线程
     1---main   main线程，用户程序入口
     */
}
