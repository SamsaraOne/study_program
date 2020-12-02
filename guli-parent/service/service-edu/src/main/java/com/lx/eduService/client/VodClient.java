package com.lx.eduService.client;

import com.lx.commonutils.R;
import com.lx.eduService.client.impl.VodClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "service-vod",fallback = VodClientImpl.class)
public interface VodClient {

    @DeleteMapping("/vod/deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vod/delete-batch")
    public R deleteBatch(@RequestParam("VidioList") List<String> VideoList);


}
