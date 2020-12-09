package com.lx.edustatic.schelu;

import com.lx.edustatic.service.StatisticsDailyService;
import com.lx.edustatic.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService dailyService;

//    @Scheduled(cron = "0/5 * * * * ?")
//    public void test(){
//        System.out.println("test schedule");
//    }
    //每天凌晨收集前一天的收据
    @Scheduled(cron = "0 0 1 * * ?")
    public void CreateDataTask(){
        dailyService.saveRegister(DateUtil.formatDate
                (DateUtil.addDays(new Date(), -1)));
    }
}
