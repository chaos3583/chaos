package com.chaos.config;

import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @Description rabbitmq连接池  
 * @author wangchunfeng
 * @date 2018年1月5日  
 *
 */
@Configuration
@ConfigurationProperties(prefix="spring.rabbitmq")
public class MQConnectionWrapper {
    private static final Logger log = LogManager.getLogger(MQConnectionWrapper.class);

    private final int INIT_SIZE = 10; // 连接池初始化大小

    private final int MAX_SIZE = 50; // 连接池的最大值
    
    private long activeTime = 60000;//临时连接存活时间

    @Value("${spring.rabbitmq.addresses}")
    private String addresses;
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.connection-timeout}")
    private int connectionTimeout;

    private volatile Vector<MQConnection> connectPool = null;// 存放数据库连接的向量
    
    /**
     * 
     * @Description 初始化连接池  
     * @return void 
     * @author wangchunfeng
     * @date 2018年1月5日
     */
    private synchronized void initPool() {
        if (null == connectPool) {
            // 创建数据库连接池
            connectPool = new Vector<MQConnection>(INIT_SIZE);
            // 循环创建数据库连接
            for (int i = 0; i < INIT_SIZE; i++) {
                MQConnection db = new MQConnection(addresses, host, port, virtualHost, connectionTimeout, username,
                        password,i);
                log.debug("创建了MQConnection连接");
                connectPool.add(db);
            }
        }
    }

    /**
     * 
     * @Description 创建临时连接  
     * @return MQConnection 
     * @author wangchunfeng
     * @date 2018年1月5日
     */
    private MQConnection createNewConectionTimer() {
        // 此方法的作用是：当获取连接的时候，如果连接不够了，才会执行这个方法创建连接
        synchronized (connectPool) {
            long onlyNum = 100020202002L;
            MQConnection db = new MQConnectionTimer(addresses, host, port, virtualHost, connectionTimeout, username,
                    password,onlyNum,activeTime);
            log.debug("创建了MQConnectionTimer连接:"+db.getOnlyNum());
            connectPool.add(db);
            return db;
        }
    }

    /**
     * 
     * @Description 获取连接  
     * @return Connection 
     * @author wangchunfeng
     * @date 2018年1月5日
     */
    public MQConnection getConnection() {
        // 初始化数据库的连接池
        this.initPool();
        log.debug(Thread.currentThread().getName()+"此时连接池中还有的连接数： " + connectPool.size());
        synchronized (connectPool) {
            while (true) {
                // 循环查找空闲的连接，直到找到位置
                for (int i = 0; i < connectPool.size(); i++) {
                    MQConnection conn = connectPool.get(i);
                    if (!conn.isUsed()) {
                        log.debug("有空闲的连接");
                        // 此连接处于空闲状态
                        if (conn instanceof MQConnectionTimer) {
                            log.debug(Thread.currentThread().getName()+"取得的链接是MQConnectionTimer:"+conn.getOnlyNum());
                            // 如果db是MQConnectionTimer对象
                            MQConnectionTimer dbTimer = (MQConnectionTimer) conn;
                            dbTimer.cancel(); // 取消定时
                        } else {
                            // 如果db是MQConnection对象
                            log.debug(Thread.currentThread().getName()+"取得的连接是MQConnection:"+conn.getOnlyNum());
                        }
                        conn.setUsed(true); // 设置此链接繁忙状态
                        return conn;
                    }

                }
                log.debug("没有空闲的连接");
                // 如果没有找到空闲的连接，则创建连接
                if (connectPool.size() < this.MAX_SIZE) {
                    // 如果连接池的大小小于要求的最大连接数,才可以创建
                    MQConnection conn = this.createNewConectionTimer();
                    conn.setUsed(true);// 新创建的连接设置为使用状态
                    log.debug(Thread.currentThread().getName()+"取得的链接是MQConnectionTimer:"+conn.getOnlyNum());
                    return conn;
                }

                // 如果连接池的大小达到了最大连接数
                if (connectPool.size() == this.MAX_SIZE) {
                    log.debug("连接池满了");
                    try {
                        // 进行等待，知道有链接进入空闲状态
                        connectPool.wait();
                        log.debug("有连接释放了------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    /**
     * 
     * @Description 释放连接  
     * @return void 
     * @author wangchunfeng
     * @date 2018年1月5日
     */
    public void releaseConnection(MQConnection conn) {
        synchronized (connectPool) {
            for (int i = 0; i < connectPool.size(); i++) {
                MQConnection db = connectPool.get(i);
                if (conn.equals(db)) {
                    //如果是临时的，执行定时任务释放连接
                    if (db instanceof MQConnectionTimer) {
                        MQConnectionTimer dbTimer = (MQConnectionTimer) db;
                        MQConTimerTask task = new MQConTimerTask(connectPool, dbTimer);
                        dbTimer.tick(task);
                        log.debug(Thread.currentThread().getName()+"释放了MQConnectionTimer的对象:"+conn.getOnlyNum());
                    } else{
                        log.debug(Thread.currentThread().getName()+"释放了MQConnection的对象:"+conn.getOnlyNum());
                    }
                    // 固定的连接，一直存在
                    db.setUsed(false);
                    connectPool.notify();
                    break;
                    
                }

            }
        }
    }
}
