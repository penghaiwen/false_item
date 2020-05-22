package com.example.demo.test;

import com.example.demo.rabbitmq.RabbitMqService;
import com.example.demo.sys.service.ISysUserService;
import com.exception.RestBean;
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




    @PostMapping("confirm")
    public RestBean confirm(){

        return RestBean.ok();
    }
}
