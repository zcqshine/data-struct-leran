package com.chowx;

/**
 * @author zcqshine
 * @date 2018/4/13
 */
public enum NumCounter {
    NUM_COUNTER;

    public static int counter = 0;

    private Integer counter(){
        return counter+1;
    }

    public Integer getCounter(){
        return counter();
    }
}
