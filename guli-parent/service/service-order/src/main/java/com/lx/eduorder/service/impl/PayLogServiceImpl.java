package com.lx.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.lx.eduorder.entity.Order;
import com.lx.eduorder.entity.PayLog;
import com.lx.eduorder.mapper.PayLogMapper;
import com.lx.eduorder.service.OrderService;
import com.lx.eduorder.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.eduorder.util.HttpClient;
import com.lx.servicebase.exceptionhandler.TestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-12-07
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;
    @Override
    public Map createNative(String orderNo) {

        try {
            QueryWrapper<Order> wrapper= new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            Order one = orderService.getOne(wrapper);
            Map m = new HashMap();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            m.put("body", one.getCourseTitle());
            m.put("out_trade_no", orderNo);
            m.put("total_fee", one.getTotalFee().multiply(new
                    BigDecimal("100")).longValue()+"");
            m.put("spbill_create_ip", "127.0.0.1");
            m.put("notify_url",
                    "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            m.put("trade_type", "NATIVE");

            HttpClient client = new
                    HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,
                    "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            Map map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", one.getCourseId());
            map.put("total_fee", one.getTotalFee());
            map.put("trade_status", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));
            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120,
                        //TimeUnit.MINUTES);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw new TestException(20001,"获取二维码失败");
        }

    }
    //查看订单支付状态
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            HttpClient client = new
                    HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,
                    "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3、返回第三方的数据
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            System.out.println(resultMap.toString());
            //6、转成Map
            //7、返回
            return resultMap;
        }catch (Exception e){
            throw new TestException(20001,"fail");
        }
    }

    //添加支付log和更新oeder状态
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //update order
        String orderNo = map.get("order_no");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        Order order = orderService.getOne(wrapper);

        if (order.getStatus()==1){return;}

        order.setStatus(1);
        orderService.updateById(order);
        //add pay log to t_log

        PayLog log = new PayLog();
        log.setOrderNo(order.getOrderNo());//支付订单号
        log.setPayTime(new Date());
        log.setPayType(1);//支付类型
        log.setTotalFee(order.getTotalFee());//总金额(分)
        log.setTradeState(map.get("trade_state"));//支付状态
        log.setTransactionId(map.get("transaction_id"));
        log.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(log);//插入到支付日志表
    }
}
