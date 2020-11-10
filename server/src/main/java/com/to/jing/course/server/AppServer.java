package com.to.jing.course.server;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.to.jing.course.dao")
public class AppServer {
    public static void main(String[] args) {
        SpringApplication.run(AppServer.class);
    }
}
