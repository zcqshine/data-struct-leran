package com.chowx.listener;

import java.util.Observer;

/**
 * @author zcqshine
 * @date 2020/4/11
 */
public interface Subject {
    /**
     * 订阅操作
     * @param observer
     */
    void attach(MyObserver observer);

    /**
     * 取消订阅
     * @param observer
     */
    void detach(MyObserver observer);

    /**
     * 通知变动
     */
    void notifyChanged();
}
