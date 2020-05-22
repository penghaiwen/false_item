package com.example.demo.order.service;

import com.example.demo.order.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老默
 * @since 2020-05-22
 */
public interface IOrderInfoService extends IService<OrderInfo> {
    void saveOrderInfo(OrderInfo info);
}
