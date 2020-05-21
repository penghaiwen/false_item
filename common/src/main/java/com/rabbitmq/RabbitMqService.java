package com.rabbitmq;

import com.dto.Order;

public interface RabbitMqService {
    void send(Order order);
}
