package com.hao.newbegin.senior;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhhao
 * @date 2022-01-20 21:54
 * @describe 测试线程池
 */
public class TestPool {
    public static void main(String[] args) {
        //1,创建服务，创建线程池
        //newFixedThreadPool：传入的参数为线程池大小
        ExecutorService service = Executors.newFixedThreadPool(10);
        //执行
        for (int i = 0; i < 10; i++) {
            service.execute(new MyThread());
        }
        //关闭链接
        service.shutdown();
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行了");
    }
}
