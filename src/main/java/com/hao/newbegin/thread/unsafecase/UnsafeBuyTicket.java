package com.hao.newbegin.thread.unsafecase;

import com.hao.newbegin.utils.ThreadUtil;


/**
 * @author zhhao
 * @date 2022-01-19 22:33
 */
//不安全的买票
//线程不安全，有负数
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket=new BuyTicket();
        new Thread(buyTicket,"苦逼打工人").start();
        new Thread(buyTicket,"返乡学生").start();
        new Thread(buyTicket,"可恶黄牛党").start();
    }
}
class BuyTicket implements Runnable{
    private int tickets=10;//票数
    private boolean flag=true;
    @Override
    public void run() {
        while (flag){
            buy();
        }
    }
    private synchronized void buy(){
        if (tickets<=0){
            flag=false;
            return;//需要加返回，不然还会执行下面的方法
        }
        ThreadUtil.sleep(1000);//调用工具类进行延时操作
        System.out.println(Thread.currentThread().getName()+"买到了第"+tickets--+"张票");
    }
}
