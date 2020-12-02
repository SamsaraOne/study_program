package com.lx.msmservice.controller;

import com.lx.commonutils.R;
import com.lx.msmservice.service.MsmService;
import com.lx.msmservice.util.RandomUtil;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@CrossOrigin
@RestController
@RequestMapping("/edumsm")
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String, String> template;

    @GetMapping("/sendMes/{phone}")
    public R sendMes(@PathVariable String phone){
        String code = template.opsForValue().get(phone);
        if (!StringUtils.isNullOrEmpty(code)){
            return R.ok();
        }
        code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code",code);
        boolean isSend = msmService.sendMsm(phone,param);
        if(isSend){
            template.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error().message("发送短信失败");
        }
    }
}
