package com.lx.eduorder.client;


import com.lx.commonutils.ordervo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {

    @PostMapping("/eduucenter/getUserInfo/{uid}")
    public UcenterMemberVo getRomoteInfo(@PathVariable("uid") String uid);

}
