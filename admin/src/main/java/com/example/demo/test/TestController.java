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
        for (int i =0 ;i<10 ;i++){
            Order order1 = new Order();
            order1.setOrderStatus(0);
            order1.setOrderId("no"+i);
            order1.setOrderName("小米"+i);
            rabbitMqService.send(order1);
        }
        return RestBean.ok();
    }


    @PostMapping("confirm")
    public RestBean confirm(){

        return RestBean.ok();
    }
}
