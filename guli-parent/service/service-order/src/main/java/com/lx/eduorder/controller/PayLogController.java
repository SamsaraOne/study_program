package com.lx.eduorder.controller;


import com.lx.commonutils.R;
import com.lx.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-12-07
 */
@CrossOrigin
@RestController
@RequestMapping("/eduorder/pay-log")
public class PayLogController {

    @Autowired
    private PayLogService payService;

    @GetMapping ("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){

        Map map = payService.createNative(orderNo);
        System.out.println(map.toString());
        return R.ok().data(map);
    }

    @GetMapping("/getPayStatus/{orderNo}")
    public R queryStatus(@PathVariable String orderNo){
        Map<String,String> map = payService.queryPayStatus(orderNo);
        if (map==null){
            return R.error().message("fail");
        }
        if (map.get("trade_status").equals("SUCCESS")){
            //添加paylog并且改变order的支付状态
            payService.updateOrderStatus(map);
            return R.ok().message("success to pay");
        }
        return R.ok().message("pay....");
    }

}

