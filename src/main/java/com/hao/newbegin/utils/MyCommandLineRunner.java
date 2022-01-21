package com.hao.newbegin.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class MyCommandLineRunner implements CommandLineRunner, Ordered {
    //继承的是Ordered接口，不是Order接口
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("@1在启动时就执行该方法，该方法实现了CommandLineRunner接口，首先执行");
    }
}
