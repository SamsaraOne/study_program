package com.lx.eduorder.service.impl;

import com.lx.eduorder.entity.Order;
import com.lx.eduorder.mapper.OrderMapper;
import com.lx.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-12-07
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {



    @Override
    public String saveOrder(String courseId, String userId) {

    }
}
