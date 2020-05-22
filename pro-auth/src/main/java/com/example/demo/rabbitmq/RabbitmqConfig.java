package com.example.demo.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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




    /**
     * channel链接工厂
     */
    @Resource
    private CachingConnectionFactory connectionFactory;

    /**
     * 监听器容器配置
     */
    @Resource
    private SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;


    //---------------------------------------订单队列------------------------------------------------------

    public static final  String order_exchange="order_exchange";
    public static final  String order_queue="order_queue";
    public static final  String order_routing_key="order_routing_key";
    /**
     * 声明订单队列的交换机
     * @return
     */
    @Bean("orderTopicExchange")
    public TopicExchange orderTopicExchange(){
        //设置为持久化 不自动删除
        return new TopicExchange(order_exchange,true,false);
    }

    /**
     * 声明订单队列
     * @return
     */
    @Bean("orderQueue")
    public Queue orderQueue(){
        return new Queue(order_queue,true);
    }

    /**
     * 将队列绑定到交换机
     * @return
     */
    @Bean
    public Binding simpleBinding(){
        return BindingBuilder.bind(orderQueue()).to(orderTopicExchange()).with(order_routing_key);
    }

    /**
     * 注入订单对列消费监听器
     */
    @Resource
    private OrderListener orderListener;

    /**
     * 声明订单队列监听器配置容器
     * @return
     */
    @Bean("orderListenerContainer")
    public SimpleMessageListenerContainer orderListenerContainer(){
        //创建监听器容器工厂
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        //将配置信息和链接信息赋给容器工厂
        factoryConfigurer.configure(factory,connectionFactory);
        //容器工厂创建监听器容器
        SimpleMessageListenerContainer container = factory.createListenerContainer();
        //指定监听器监听的队列
        container.setQueues(orderQueue());
        //指定监听器
        container.setMessageListener(orderListener);
        return container;
    }


    //--------------------------------------------支付队列-------------------------------------------------

    /**
     * 死信队列：也叫延迟队列，可以设置队列中的消息过期时间
     * 死信交换机：死信队列声明同时声明死信交换机，死信队列中的消息到期后会通过死信交换机进入到绑定的真正队列做监听消费处理
     */
    public static final String pay_order_delay_exchange="pay_order_delay_exchange";
    public static final String pay_order_delay_queue="pay_order_delay_queue";
    public static final String pay_order_delay_routing_key="pay_order_delay_routing_key";


    public static final String pay_order_exchange="pay_order_exchange";
    public static final String pay_order_queue="pay_order_queue";
    public static final String pay_order_routing_key="pay_order_routing_key";



    /**
     * 死信队列，十五分钟超时
     * @return
     */
    @Bean
    public Queue payDeadLetterQueue(){
        Map args = new HashMap();
        //声明死信交换机
        args.put("x-dead-letter-exchange",pay_order_delay_exchange);
        //声明死信routingkey
        args.put("x-dead-letter-routing-key",pay_order_delay_routing_key);
        //声明死信队列中的消息过期时间
        //args.put("x-message-ttl",env.getProperty("pay.mq.ttl",int.class));
        //创建死信队列
        return new Queue(pay_order_delay_queue,true,false,false,args);
    }

    /**
     * 支付队列交换机（主交换机）
     * @return
     */
    @Bean
    public TopicExchange payTopicExchange(){
        return new TopicExchange(pay_order_exchange,true,false);
    }

    /**
     * 将主交换机绑定到死信队列
     * @return
     */
    @Bean
    public Binding payBinding(){
        return BindingBuilder.bind(payDeadLetterQueue()).to(payTopicExchange()).with(pay_order_routing_key);
    }

    /**
     * 支付队列（主队列）
     * @return
     */
    @Bean
    public Queue payQueue(){
        return new Queue(pay_order_queue,true);
    }

    /**
     * 死信交换机
     * @return
     */
    @Bean
    public TopicExchange payDeadLetterExchange(){
        return new TopicExchange(pay_order_delay_exchange,true,false);
    }

    /**
     * 将主队列绑定到死信交换机
     * @return
     */
    @Bean
    public Binding payDeadLetterBinding(){
        return BindingBuilder.bind(payQueue()).to(payDeadLetterExchange()).with(pay_order_delay_routing_key);
    }

    /**
     * 注入支付监听器
     */
    @Resource
    private PayListener payListener;

    /**
     * 支付队列监听器容器
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer payMessageListenerContainer(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory,connectionFactory);
        SimpleMessageListenerContainer listenerContainer = factory.createListenerContainer();
        listenerContainer.setMessageListener(payListener);
        listenerContainer.setQueues(payQueue());
        return listenerContainer;
    }


}
