package com.chowx.listener;

/**
 * @author zcqshine
 * @date 2020/4/11
 */
public class ObserverImpl implements MyObserver {
    @Override
    public void update() {
        System.out.println("接收到通知");
    }
}
