package com.hao.newbegin.thread.unsafecase;

import com.hao.newbegin.utils.ThreadUtil;

import javax.xml.crypto.Data;

/**
 * @author zhhao
 * @date 2022-01-19 22:59
 */
public class UnsafeBank {
    public static void main(String[] args) {
        //账户
        Account account = new Account(100, "结婚基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girlFriend = new Drawing(account, 100, "女朋友");
        you.start();
        girlFriend.start();
    }
}

//账户
class Account {
    int money;//总金额
    String name;//账户

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

//银行：模拟取款
class Drawing extends Thread {
    Account account;//账户
    //取了多少钱
    int drawingMoney;
    //现在手里有多少钱
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;
    }

    //取钱
    //此处加锁不行了，因为synchronized默认锁的是this
    @Override
    public void run() {
        //同步块
        synchronized (account) {
            //判断有没有钱
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够了，取不出来了");
                return;
            }
            //模拟一个延时,会发现出问题了
            ThreadUtil.sleep(1000);
            //卡里的余额=余额-你取的钱
            account.money -= drawingMoney;
            //你手里的钱
            nowMoney += drawingMoney;
            System.out.println(account.name + "余额为：" + account.money);
            //this.getName()等价于Thread.currentThread().getName()
            System.out.println(this.getName() + "手里的钱：" + nowMoney);
        }
    }
}
