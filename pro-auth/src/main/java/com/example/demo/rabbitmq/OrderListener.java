package com.example.demo.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.example.demo.order.entity.OrderInfo;
import com.example.demo.order.service.IOrderInfoService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class OrderListener implements ChannelAwareMessageListener {

    @Resource
    private IOrderInfoService orderInfoService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try{
            //获取交付tag
            long tag = message.getMessageProperties().getDeliveryTag();
            String str = new String(message.getBody(),"utf-8");
            log.info("接收到的消息：{}",str);
            OrderInfo info = JSON.parseObject(str,OrderInfo.class);
            //下单，操作数据库
            orderInfoService.saveOrderInfo(info);
            //确认消费
            channel.basicAck(tag,true);
        }catch(Exception e){
            log.error("消息监听确认机制发生异常：",e.fillInStackTrace());
        }
    }
}
