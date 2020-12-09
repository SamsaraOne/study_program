package com.lx.edustatic.controller;


import com.lx.commonutils.R;
import com.lx.edustatic.client.UcenterClient;
import com.lx.edustatic.entity.StatisticsDaily;
import com.lx.edustatic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.ApiListing;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author samsara
 * @since 2020-12-08
 */
@CrossOrigin
@RestController
@RequestMapping("/edustatic/statistics")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService dataService;



    @PostMapping("/addStatic/{day}")
    public R addStatics(@PathVariable String day){

        dataService.saveRegister(day);
        return R.ok();
    }
    @GetMapping("/getData/{type}/{begin}/{end}")
    public R getData(@PathVariable String type,
                     @PathVariable String begin,
                     @PathVariable String end){
        Map map = dataService.getData(type,begin,end);
        return R.ok().data(map);
    }
}

