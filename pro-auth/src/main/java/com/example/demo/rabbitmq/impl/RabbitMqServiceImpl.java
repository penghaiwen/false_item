package com.example.demo.rabbitmq.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.order.entity.OrderInfo;
import com.example.demo.rabbitmq.RabbitMqService;
import com.example.demo.rabbitmq.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageBuilder;
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
    public void pay(OrderInfo order) {

        log.info("【订单生成时间】" + new Date().toString() +"【1分钟后检查订单是否已经支付】" + order.toString() );
        rabbitTemplate.convertAndSend(RabbitmqConfig.pay_order_delay_exchange, RabbitmqConfig.pay_order_routing_key, order, message -> {
            message.getMessageProperties().setExpiration("10000");
            return message;
        });

    }

    @Override
    public void sendOrder(OrderInfo order) {
        log.info("【订单生成时间】" + new Date().toString() +"【订单】" + order.toString() );
        rabbitTemplate.convertAndSend(RabbitmqConfig.order_exchange, RabbitmqConfig.order_routing_key,message -> {
            message=  MessageBuilder.withBody(JSON.toJSONString(order).getBytes()).build();
            return message;
        });
    }
}
