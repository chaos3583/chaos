package com.chaos.config;

import java.util.TimerTask;
import java.util.Vector;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @Description 移除临时mq连接TimeTask 
 * @author wangchunfeng
 * @date 2018年1月5日  
 *
 */
public class MQConTimerTask extends TimerTask {
    private static final Logger log = LogManager.getLogger(MQConTimerTask.class);
    private Vector<MQConnection> connectPool = null;
    private MQConnectionTimer MQTimer;

    public MQConTimerTask(Vector<MQConnection> connectPool, MQConnectionTimer MQTimer) {
        super();
        this.connectPool = connectPool;
        this.MQTimer = MQTimer;
    }

    @Override
    public void run() {
        // 将过期的数据库连接移除
        connectPool.remove(MQTimer);
        try {
            MQTimer.getConn().close();
            log.debug(Thread.currentThread().getName()+"==============移除超出生命周期的MQ连接！==============");
        } catch (Exception e) {
            log.error(Thread.currentThread().getName()+"==============关闭超出生命周期的MQ连接出现错误=============="+JSONObject.toJSONString(MQTimer.getOnlyNum()),e);
        }
    }

}