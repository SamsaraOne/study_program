package com.lx.eduorder.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.lx")
@MapperScan("com.lx.eduorder.mapper")
public class OrderConfig {
    @Bean
    public ISqlInjector iSqlInjector(){

        return new LogicSqlInjector();
    }
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return  new PaginationInterceptor();
    }
}
