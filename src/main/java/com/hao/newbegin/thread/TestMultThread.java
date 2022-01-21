package com.hao.newbegin.thread;

import sun.awt.windows.ThemeReader;

/**
 * 模拟一个抢票的场景，初识并发问题
 */
//发现问题：多个线程同时操作同一个对象，会出现问题
public class TestMultThread implements Runnable{
    private int ticketNum=10;
    @Override
    public void run() {
        while (true){
            if (ticketNum<=0){
                break;
            }
            //模拟延时
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //Thread.currentThread().getName()获得线程名称
            System.out.println(Thread.currentThread().getName()+"抢到了第"+ticketNum--+"张票");
        }
    }

    public static void main(String[] args) {
        TestMultThread testMultThread=new TestMultThread();
        //给线程命名
        new Thread(testMultThread,"学生").start();
        new Thread(testMultThread,"老师").start();
        new Thread(testMultThread,"其他人员").start();
    }
}
