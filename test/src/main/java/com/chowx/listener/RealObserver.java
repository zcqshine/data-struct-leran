package com.chowx.listener;

import java.util.Observable;
import java.util.Observer;

/**
 * @author zcqshine
 * @date 2020/4/11
 */
public class RealObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("调用了-->");
    }
}
