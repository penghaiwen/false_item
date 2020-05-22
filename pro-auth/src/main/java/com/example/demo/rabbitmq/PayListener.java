package com.example.demo.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PayListener implements ChannelAwareMessageListener {




    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        Long tag = message.getMessageProperties().getDeliveryTag();
        try {
            String str = new String(message.getBody(), "utf-8");
            log.info("接收到的消息：{}",str);
            JSONObject json = JSON.parseObject(str);
            String orderId = json.getString("orderNo");
            channel.basicAck(tag, true);
        }catch(Exception e){
            log.error("支付消息消费出错：{}",e.getMessage());
            log.error("出错的tag:{}",tag);
        }
    }
}
