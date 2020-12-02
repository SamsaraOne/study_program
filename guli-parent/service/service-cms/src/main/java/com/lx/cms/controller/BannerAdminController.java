package com.lx.cms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lx.cms.entity.CrmBanner;
import com.lx.cms.service.CrmBannerService;
import com.lx.commonutils.R;
import com.mysql.cj.x.protobuf.Mysqlx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/cmsadmin")
public class BannerAdminController {

    @Autowired
    private CrmBannerService bannerService;
//后台banner增删改查
    @GetMapping("/BannerPage/{page}/{limit}")
    public R getBannerPage(@PathVariable long page,
                           @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        List<CrmBanner> data = pageBanner.getRecords();
        long total = pageBanner.getTotal();
        return R.ok().data("data",data).data("total",total);
    }
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner banner){
        bannerService.save(banner);
        return R.ok();
    }
    @DeleteMapping("/deleteBanner/{id}")
    public R deleteBanner(@PathVariable String id){
        bannerService.removeById(id);
        return R.ok();
    }
    @GetMapping("/getBannerById/{id}")
    public R getBannerById(@PathVariable String id){
        CrmBanner data = bannerService.getById(id);
        return R.ok().data("data",data);
    }
    @PostMapping("/updateBanner")
    public R updateBanner(@RequestBody CrmBanner banner){
        boolean res = bannerService.updateById(banner);
        if(res){
            return R.ok();
        }else{
            return R.error();
        }

    }
    @PostMapping("/updateBannerById/{id}")
    public R updateBannerById(@PathVariable String id,
                              @RequestBody CrmBanner banner){
        banner.setId(id);
        boolean res = bannerService.updateById(banner);
        if(res){
            return R.ok();
        }else{
            return R.error();
        }

    }
}

