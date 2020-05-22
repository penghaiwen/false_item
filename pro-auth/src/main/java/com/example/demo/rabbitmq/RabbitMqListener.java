package com.example.demo.rabbitmq;

import com.example.demo.order.entity.OrderInfo;
import com.example.demo.order.service.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
@Slf4j
@EnableRabbit
@Configuration
public class RabbitMqListener {
    @Resource
    private IOrderInfoService orderInfoService;


    @RabbitListener(queues = {RabbitmqConfig.pay_order_delay_queue})
    public void orderQueue(OrderInfo order) {
        log.info("###########################################");
        log.info("【orderDelayQueue 监听的消息】 - 【消费时间】 - [{}]- 【订单内容】 - [{}]",  new Date(), order.toString());
        log.info("订单号：{},支付超时",order.getOrderNo());
        log.info("###########################################");
    }




}
