package com.chowx;

/**
 * @author zcqshine
 * @date 2018/4/13
 */
public class EnumSingleton {
    private EnumSingleton(){}

    public static EnumSingleton getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    enum Singleton {
        INSTANCE;

        private EnumSingleton singleton;

        Singleton(){
            singleton = new EnumSingleton();
        }

        public EnumSingleton getSingleton() {
            return singleton;
        }
    }
}
