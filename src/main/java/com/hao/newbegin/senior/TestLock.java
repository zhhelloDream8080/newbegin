package com.hao.newbegin.senior;

import com.hao.newbegin.utils.ThreadUtil;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhhao
 * @date 2022-01-20 15:37
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2=new TestLock2();
        new Thread(testLock2,"线程1").start();
        new Thread(testLock2,"线程2").start();
        new Thread(testLock2,"线程3").start();
    }
}
class TestLock2 implements Runnable{
    int ticketNum=10;
    //定义lock锁
    //final表示“不可修改的”
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                lock.lock();//加锁
                if (ticketNum>0){
                    ThreadUtil.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"===>"+ticketNum--);
                }else {
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();//解锁
            }
        }
    }
}
