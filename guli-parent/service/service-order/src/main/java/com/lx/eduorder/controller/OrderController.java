package com.lx.eduorder.controller;


import com.lx.commonutils.JwtUtils;
import com.lx.commonutils.R;
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

    @RequestMapping("/createorder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNo = orderService.saveOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("oderNo",orderNo);
    }
}

