package com.example.demo.order.service.impl;

import com.example.demo.order.entity.OrderInfo;
import com.example.demo.order.mapper.OrderInfoMapper;
import com.example.demo.order.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 老默
 * @since 2020-05-22
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {

}
