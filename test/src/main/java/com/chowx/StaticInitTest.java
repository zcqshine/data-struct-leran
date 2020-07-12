package com.chowx;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcqshine
 * @date 2018/4/13
 */
public class StaticInitTest {
    private static List<Integer> dataList = null;

    static {
        dataList = Singleton.INSTANCE.init();
    }

    private enum Singleton{
        INSTANCE;

        private List<Integer> list;

        Singleton(){
            fillData();
        }

        /**
         * 初始化数据
         */
        private void fillData(){
            list = new ArrayList<>(6);
            for (int i = 0; i<6; i++){
                list.add(i);
            }
        }

        /**
         * 初始化数据的入口
         * @return
         */
        public List<Integer> init(){
            return list;
        }
    }

    public static void main(String[] args) {
        System.out.println(StaticInitTest.dataList.size());
    }
}
