package com.lx.cms.config;

import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com/lx/cms/mapper")
@ComponentScan(basePackages = "com.lx")
public class BannerConfig {

    @Bean
    LogicSqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
    @Bean
    PaginationInterceptor pagingInter(){
        return new PaginationInterceptor();
    }
}
