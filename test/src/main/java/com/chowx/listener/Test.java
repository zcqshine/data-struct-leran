package com.chowx.listener;

/**
 * @author zcqshine
 * @date 2020/4/11
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new SubjectImpl();
        MyObserver observer = new ObserverImpl();
        subject.attach(observer);
        subject.notifyChanged();
    }
   
    
}
