package com.rabbitmq.impl;

import com.dto.Order;
import com.rabbitmq.RabbitMqService;
import com.rabbitmq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class RabbitMqServiceImpl implements RabbitMqService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(Order order) {

        log.info("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】" + order.toString() );
        rabbitTemplate.convertAndSend(RabbitmqConfig.ORDER_DELAY_EXCHANGE, RabbitmqConfig.ORDER_DELAY_ROUTING_KEY, order, message -> {
            message.getMessageProperties().setExpiration("10000");
            return message;
        });


      //  rabbitTemplate.convertAndSend("user.order.delay_exchange", "user.order.delay_key", "orderMaster", getMessagePostProcessor());

    }

    private MessagePostProcessor getMessagePostProcessor() {
        return message -> {
            message.getMessageProperties().setExpiration("10000");
            return message;
        };
    }
}
