package com.hao.newbegin.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 该类和MyCommandRunner都是启动前加载类，通过继承两个
 * 接口ApplicationRunner和CommandLineRunner，
 * 可以在项目启动时就执行某些操作，比如从数据库中加载某些信息或者是提前做
 * 一些数据的初始化
 */
@Component
@Order(value = 2)//使用Order注解设置两个类的执行顺序，另一个是继承Ordered接口实现
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("@2在启动时就执行该方法，该方法继承了ApplicationRunner接口，其次执行");
    }
}
