package com.example.demo.test;

import com.dto.Order;
import com.example.demo.sys.service.ISysUserService;
import com.exception.RestBean;
import com.rabbitmq.RabbitMqService;
import com.redis.RedisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    private ISysUserService sysUserService;

    @Resource
    private RedisService redisService;

    @Resource
    private RabbitMqService rabbitMqService;

    @PostMapping("user")
    public RestBean test(){
        Order order1 = new Order();
        order1.setOrderStatus(0);
        order1.setOrderId("123456");
        order1.setOrderName("小米6");

        Order order2 = new Order();
        order2.setOrderStatus(1);
        order2.setOrderId("456789");
        order2.setOrderName("小米8");


        rabbitMqService.send(order1);
        rabbitMqService.send(order2);
        return RestBean.ok();
    }
}
