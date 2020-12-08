package com.lx.edustatic.controller;


import com.lx.commonutils.R;
import com.lx.edustatic.entity.StatisticsDaily;
import com.lx.edustatic.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/addStatic")
    public R addStatics(){

        return R.ok();
    }

}

