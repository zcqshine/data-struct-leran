package com.chowx.listener;

import java.util.Observable;

/**
 * @author zcqshine
 * @date 2020/4/11
 */
public class RealSubject extends Observable {
    public void makeChanged(){
        setChanged();
        notifyObservers();
    }
}
