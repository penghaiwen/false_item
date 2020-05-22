package com.example.demo.rabbitmq;

import com.example.demo.order.entity.OrderInfo;

public interface RabbitMqService {
    void pay(OrderInfo order);


    void sendOrder(OrderInfo order);
}
