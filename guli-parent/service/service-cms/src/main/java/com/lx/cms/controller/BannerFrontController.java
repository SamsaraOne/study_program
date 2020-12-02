package com.lx.cms.controller;


import com.lx.cms.entity.CrmBanner;
import com.lx.cms.service.CrmBannerService;
import com.lx.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-11-25
 */
@CrossOrigin
@RestController
@RequestMapping("/cmsfront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService bannerService;

    // 查询所有数据
    @GetMapping("/getAllBanner")
    public R getAllBanner(){
        List<CrmBanner> data = bannerService.selectAllBanner();

        return R.ok().data("data",data);
    }

}

