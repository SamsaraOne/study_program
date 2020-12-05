package com.lx.eduService.client;

import com.lx.eduService.client.impl.MemberClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient(name = "service-service-ucenter", fallback = MemberClientImpl.class )
public interface MemberClient {


}
