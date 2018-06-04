package com.chaos.config;

import java.util.Timer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 
 * @Description 连接池中临时mq连接类  
 * @author wangchunfeng
 * @date 2018年1月5日  
 *
 */
public class MQConnectionTimer extends MQConnection {
    private static final Logger log = LogManager.getLogger(MQConnectionTimer.class);
    private long activeTime;
    private Timer timer;
    private volatile boolean hasCancel;


    public MQConnectionTimer(String addresses, String host, int port, String virtualHost, int connectionTimeout,
            String username, String password,long onlyNum,long activeTime) {
        super(addresses, host, port, virtualHost, connectionTimeout, username,
                password,onlyNum);
        this.activeTime = activeTime;
        timer = new Timer();
        hasCancel = false;
    }

    
    public void tick(MQConTimerTask task) {
        synchronized (this) {
            if(this.hasCancel){
                this.timer = null;
                this.timer = new Timer();
                this.hasCancel = false;
            }
        }
        try {
            this.timer.schedule(task, activeTime);
            log.debug("=============定时开始=============");
        } catch (IllegalStateException e) {
            log.error("=============已经存在task了==============",e);
        }

    }

    public void cancel() {
        synchronized (this) {
            this.timer.cancel();
            this.hasCancel = true;
        }
        log.debug("============取消定时==================");
    }


    public long getActiveTime() {
        return activeTime;
    }


    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }


    public boolean isHasCancel() {
        return hasCancel;
    }


    public void setHasCancel(boolean hasCancel) {
        this.hasCancel = hasCancel;
    }

}
