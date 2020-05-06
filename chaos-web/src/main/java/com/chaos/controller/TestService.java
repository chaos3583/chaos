package com.chaos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chaos.mq.MessageConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: chaos
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-04-30 16:34
 **/
@Component
public class TestService {

    @RabbitListener(queues = MessageConstants.CHAOS_QUEUE_NAME)
    public void testMQ(Message message) throws Exception{
        byte[] body = message.getBody();
        System.out.println(new String(body));
        JSONObject resultMap = JSON.parseObject(new String(body));
        if("夏夜".equals(resultMap.get("name"))){
            throw new Exception("测试MQ retry");
        }
    }
}
