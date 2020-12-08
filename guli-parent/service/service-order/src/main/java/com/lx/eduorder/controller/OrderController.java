package com.lx.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.commonutils.JwtUtils;
import com.lx.commonutils.R;
import com.lx.eduorder.entity.Order;
import com.lx.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-07
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/createorder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){

        String uid = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.saveOrder(courseId, uid);

        return R.ok().data("oderNo",orderNo);

    }

    @GetMapping("/getOrder/{orderNo}")
    public R getOrder(@PathVariable String orderNo){
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("order",order);
    }








}

