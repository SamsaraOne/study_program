package com.lx.eduorder.service.impl;

import com.lx.commonutils.ordervo.OrderCourseVo;
import com.lx.commonutils.ordervo.UcenterMemberVo;
import com.lx.eduorder.client.CourseClient;
import com.lx.eduorder.client.UcenterClient;
import com.lx.eduorder.entity.Order;
import com.lx.eduorder.mapper.OrderMapper;
import com.lx.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Autowired
    CourseClient courseClient;
    @Autowired
    UcenterClient ucenterClient;

    @Override
    public String saveOrder(String courseId, String userId) {

        OrderCourseVo orderCourseVo = courseClient.getInfo(courseId);
        UcenterMemberVo ucenterMemberVo = ucenterClient.getRomoteInfo(userId);


        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setCourseId(courseId);
        order.setCourseTitle(orderCourseVo.getTitle());
        order.setCourseCover(orderCourseVo.getCover());
        order.setTeacherName("test");
        order.setTotalFee(orderCourseVo.getPrice());
        order.setMemberId(userId);
        order.setMobile(ucenterMemberVo.getMobile());
        order.setNickname(ucenterMemberVo.getNickname());
        order.setStatus(0);
        order.setPayType(1);

        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
