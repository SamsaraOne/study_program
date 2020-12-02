package com.lx.eduService.config;


import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.lx.eduService.mapper")

public class Educonfig {
    @Bean
    public ISqlInjector iSqlInjector(){

        return new LogicSqlInjector();
    }
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return  new PaginationInterceptor();
    }
}
