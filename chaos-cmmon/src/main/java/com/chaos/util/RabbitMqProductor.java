package com.chaos.util;

import com.chaos.redis.JedisUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: wms
 * * @description:
 * * @author: liaopeng
 * * @create: 2020-04-23 16:07
 **/
@Component
public class RabbitMqProductor implements RabbitTemplate.ConfirmCallback{
    private Log log = LogFactory.getLog(getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack){
            log.info("发送mq消息失败:"+cause);
            String id = correlationData.getId();
            String value = jedisUtil.get(id);
            String[] array = value.split("~");
            if (array.length==3){
                send(array[0],array[1],array[2]);//重发
            }
        }
    }

     /**
     * 发送mq消息
     * @param exchangeName 交换机
     * @param routingKey    路由键
     * @param msg       消息体
     */
    public void send(String exchangeName,String routingKey,String msg){
        try{
            String id = UUIDUtils.getUUID();//生成消息id
            jedisUtil.setEx(id,10*60,exchangeName+"~"+routingKey+"~"+msg);

            CorrelationData correlationData = new CorrelationData(id);
            rabbitTemplate.setMandatory(true);//交换机无法匹配到队列时返回false
            rabbitTemplate.setConfirmCallback(this);//消息确认回调
            rabbitTemplate.convertAndSend(exchangeName, routingKey, msg,correlationData);
        }catch(Exception e){
            e.printStackTrace();
            log.error("convertAndSend消息异常,"+e.getMessage(),e);
        }

    }
}
