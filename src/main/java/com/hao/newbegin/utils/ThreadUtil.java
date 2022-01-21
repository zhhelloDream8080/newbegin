package com.hao.newbegin.utils;

/**
 * @author zhhao
 * @date 2022-01-19 22:43
 */
public class ThreadUtil {
    public static void sleep(long million){
        try {
            Thread.sleep(million);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
