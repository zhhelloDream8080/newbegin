package com.hao.newbegin.thread;

import com.hao.newbegin.utils.ThreadUtil;

/**
 * @author zhhao
 * @date 2022-01-20 14:48
 */
//死锁：多个线程互相抱着对方需要的资源，然后形成僵持
public class TestDeadLock {
    public static void main(String[] args) {
        Makeup g1=new Makeup(0,"灰姑娘");
        Makeup g2=new Makeup(1,"白雪公主");
        g1.start();
        g2.start();
    }
}
//口红
class LipStick{

}
//镜子
class Mirror{

}
class Makeup extends Thread{
    //需要的资源只有一份，用static来保证只有一份
    static LipStick lipStick=new LipStick();
    static Mirror mirror=new Mirror();
    int choice;//选择
    String girlName;//使用化妆品的人
    public Makeup(int choice,String girlName){
        this.choice=choice;
        this.girlName=girlName;
    }
    @Override
    public void run() {
        //化妆
        makeup();
    }
    //化妆，互相持有对方的锁，就是需要拿到对方的资源
    private void makeup(){
        if (choice==0){//choice为0表示想先拿到口红
            synchronized (lipStick){//获得口红的锁
                System.out.println(this.girlName+"获得口红的锁");
                ThreadUtil.sleep(1000);
            }
            synchronized (mirror){//一秒种后想获得镜子
                System.out.println(this.girlName+"获得了镜子的锁");
            }
        }else {
            synchronized (mirror){//获得镜子的锁
                System.out.println(this.girlName+"获得镜子的锁");
                ThreadUtil.sleep(2000);
            }
            synchronized (lipStick){//一秒种后想获得口红
                System.out.println(this.girlName+"获得了口红的锁");
            }
        }
    }
}