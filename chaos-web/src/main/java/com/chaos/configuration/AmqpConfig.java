package com.chaos.configuration;

import com.chaos.mq.MessageConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AmqpConfig {

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(MessageConstants.EXCHANGE_NAME);
    }

    /**
     * 定义队列
     * @return
     */
    @Bean
    Queue incomingQueue() {
        return QueueBuilder.durable(MessageConstants.CHAOS_QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", MessageConstants.DEAD_LETTER_QUEUE_NAME)
                .build();
    }

    /**
     * 绑定routingKey与队列
     * @return
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(incomingQueue()).to(exchange()).with(MessageConstants.ROUTING_KEY_NAME);
    }

    /**
     * 死信队列
     * @return
     */
    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(MessageConstants.DEAD_LETTER_QUEUE_NAME).build();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
