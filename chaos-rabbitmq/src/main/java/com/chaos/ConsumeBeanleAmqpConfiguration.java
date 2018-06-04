package com.chaos;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chaos.biz.ChaosTestMq;
import com.chaos.config.RabbitMqConfig;

/**
 * 消费者BEAN配置
 *
 * @author zlj
 * @create 2018-03-09 下午3:14
 **/
@Configuration
@AutoConfigureAfter(RabbitMqConfig.class)
public class ConsumeBeanleAmqpConfiguration {
	/**
	 * 入库通知单作废消息队列处理bean
	 * @param connectionFactory
	 * @return
	 */
	 @Bean
	 public ChaosTestMq chaosTestMq(ConnectionFactory connectionFactory) {
		 ChaosTestMq container = new ChaosTestMq(connectionFactory);
		 return container;
	 }
}
