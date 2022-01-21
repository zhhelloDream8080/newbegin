package com.hao.newbegin.thread;

public class RunnaleExtend implements Runnable {
    @Override
    public void run() {
        for (int i=0;i<20;i++){
            System.out.println("我在学线程"+i);
        }
    }
    public static void main(String[] args){
        //创建实现类对象
        RunnaleExtend runnaleExtend=new RunnaleExtend();
        //创建代理类对象
        Thread thread = new Thread(runnaleExtend);
        //启动
        thread.start();
        for (int i=0;i<50;i++){
            System.out.println("我在写代码"+i);
        }
    }
}
