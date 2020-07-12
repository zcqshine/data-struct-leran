package com.chowx.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * @author zcqshine
 * @date 2020/4/11
 */
public class SubjectImpl implements Subject {
    private List<MyObserver> observerList = new ArrayList<>();
    @Override
    public void attach(MyObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(MyObserver observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyChanged() {
        for (MyObserver observer : observerList) {
            observer.update();
        }
    }
}
