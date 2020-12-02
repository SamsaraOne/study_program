package com.lx.eduucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.lx")
@MapperScan(basePackages = "com/lx/eduucenter/mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class Ucenter {
    public static void main(String[] args) {
        SpringApplication.run(Ucenter.class,args);
    }
}
