package com.hao.newbegin.thread;

/**
 * @author zhhao
 * @date 2022-01-19 17:16
 */
public class TestThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        //创建线程并启动
        TestJoin testJoin=new TestJoin();
        Thread thread=new Thread(testJoin);
        thread.start();
        System.out.println("线程状态是："+thread.getState());
        for (int i=0;i<200;i++){
            if (i==100){
                thread.join();//主线程执行期间开启的线程来插队
            }
            System.out.println("主线程在执行"+i);
        }
    }
}
class TestJoin implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println("线程VIP来插队了"+i);
        }
    }
}
