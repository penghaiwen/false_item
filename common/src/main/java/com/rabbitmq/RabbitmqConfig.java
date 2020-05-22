package com.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    //订单支付队列
    public static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";
    //订单支付交换机
    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";

    //订单队列
    public static final String ORDER_QUEUE_NAME = "user.order.queue";
    //订单交换机
    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";


    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";


    public static final String ORDER_ROUTING_KEY = "order";


    // 订单队列
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }
    // 订单交换机
    @Bean
    public TopicExchange orderTopicExchange() {
        return new TopicExchange(ORDER_EXCHANGE_NAME);
    }

    // 将订单队列绑定到订单交换机上
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderTopicExchange()).with(ORDER_ROUTING_KEY);
    }



    @Bean
    public Queue delayOrderQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", ORDER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", ORDER_ROUTING_KEY);
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, params);
    }

    @Bean
    public DirectExchange orderDelayExchange() {
        return new DirectExchange(ORDER_DELAY_EXCHANGE);
    }
    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayOrderQueue()).to(orderDelayExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }




    @Resource
    private OrderListener orderListener;

    @Resource
    private CachingConnectionFactory connectionFactory;


    @Resource
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;


    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(){
        //创建监听器容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //将配置信息和链接信息赋给容器工厂
        factoryConfigurer.configure(factory,connectionFactory);
        //容器工厂创建监听器容器
        SimpleMessageListenerContainer container = factory.createListenerContainer();
        //指定监听器
        container.setMessageListener(orderListener);
        //指定监听器监听的队列
        container.setQueues(orderQueue());
        return container;

    }





}
