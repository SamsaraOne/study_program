package com.lx.edustatic.service;

import com.lx.edustatic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author samsara
 * @since 2020-12-08
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void saveRegister(String day);

    Map getData(String type, String begin, String end);
}
