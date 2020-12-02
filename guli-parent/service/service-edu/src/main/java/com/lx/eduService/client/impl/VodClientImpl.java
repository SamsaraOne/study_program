package com.lx.eduService.client.impl;

import com.lx.commonutils.R;
import com.lx.eduService.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientImpl implements VodClient {
    @Override
    public R deleteVideo(String videoId) {
        return R.error().message("熔断发生");
    }

    @Override
    public R deleteBatch(List<String> VideoList) {
        return R.error().message("熔断发生");
    }
}
