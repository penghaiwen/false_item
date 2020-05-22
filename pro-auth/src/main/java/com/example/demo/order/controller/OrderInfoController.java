package com.example.demo.order.controller;


import com.example.demo.order.entity.OrderInfo;
import com.exception.RestBean;
import com.example.demo.rabbitmq.RabbitMqService;
import com.utils.SnowflakeIdUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老默
 * @since 2020-05-22
 */
@RestController
@RequestMapping("/test/order/order_info")
public class OrderInfoController {
    static final SnowflakeIdUtils idWorker = new SnowflakeIdUtils(3, 1);


    @Resource
    private RabbitMqService rabbitMqService;




    @PostMapping("order")
    public RestBean order(Long goodsId){
        OrderInfo order = new OrderInfo();
        order.setGoodsId(goodsId);

        order.setUserId(idWorker.nextId());
        order.setPayStatus(1);
        long nextId = idWorker.nextId();
        order.setOrderNo("No"+nextId);
        rabbitMqService.send(order);
        rabbitMqService.sendOrder(order);
        return RestBean.ok();
    }


}
