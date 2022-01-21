package com.hao.newbegin.thread;

public class ThreadExtend extends Thread{
    @Override
    public void run(){
        for (int i=0;i<20;i++){
            System.out.println("创建的线程开始执行"+i);
        }
    }
    public static void main(String[] args){
        //main线程，主线程

        //创建一个线程对象
        ThreadExtend threadExtend=new ThreadExtend();
        //调用线程的start()方法开启线程
        threadExtend.start();

        //主方法里也开始执行
        for (int i=0;i<20;i++){
            System.out.println("主线程开始执行"+i);
        }
    }
}

