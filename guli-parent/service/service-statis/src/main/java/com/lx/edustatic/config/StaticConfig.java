package com.lx.edustatic.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.lx")
@MapperScan("com.lx.edustatic.mapper")
public class StaticConfig {
    @Bean
    public ISqlInjector iSqlInjector(){

        return new LogicSqlInjector();
    }
    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return  new PaginationInterceptor();
    }
}
