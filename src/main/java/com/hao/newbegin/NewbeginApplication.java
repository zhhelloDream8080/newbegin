package com.hao.newbegin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hao.newbegin.test.dao")
public class NewbeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewbeginApplication.class, args);
    }

}
