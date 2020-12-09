package com.lx.edustatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.edustatic.client.UcenterClient;
import com.lx.edustatic.entity.StatisticsDaily;
import com.lx.edustatic.mapper.StatisticsDailyMapper;
import com.lx.edustatic.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.chrono.Era;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author samsara
 * @since 2020-12-08
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void saveRegister(String day) {
        int register_num = ucenterClient.getNum(day);
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);
        StatisticsDaily statis = new StatisticsDaily();
        statis.setRegisterNum(register_num);
        statis.setDateCalculated(day);
        statis.setCourseNum(RandomUtils.nextInt(1,100));
        statis.setLoginNum(RandomUtils.nextInt(1,100));
        statis.setVideoViewNum(RandomUtils.nextInt(1,100));
        baseMapper.insert(statis);

    }

    @Override
    public Map getData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
//        wrapper.select(type);
        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);

        //要求数组数据结构故而后端采用list集合
        List<String> dateList = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        for(StatisticsDaily res: statisticsDailies){
            dateList.add(res.getDateCalculated());
            switch (type){
                case "register_num":
                    num.add(res.getRegisterNum());
                    break;
                case "login_num":
                    num.add(res.getLoginNum());
                    break;
                case "video_view_num":
                    num.add(res.getVideoViewNum());
                    break;
                case "course_num":
                    num.add(res.getCourseNum());
                    break;
                default:
                    break;
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("dateList",dateList);
        map.put("numList",num);
        return map;
    }

}
