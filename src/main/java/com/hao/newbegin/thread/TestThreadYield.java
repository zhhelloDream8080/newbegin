package com.hao.newbegin.thread;

/**
 * @author zhhao
 * @date 2022-01-19 17:01
 */
//测试礼让线程
// 线程不一定成功，看CPU心情
public class TestThreadYield {
    public static void main(String[] args) {
        MyYield myYield=new MyYield();
        new Thread(myYield,"线程1").start();
        new Thread(myYield,"线程2").start();
    }
}

class MyYield implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程开始执行");
        Thread.yield();//礼让
        System.out.println(Thread.currentThread().getName()+"线程停止执行");
    }
}
