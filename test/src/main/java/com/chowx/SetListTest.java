package com.chowx;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zcqshine on 2017/3/24.
 */
public class SetListTest {
    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");

        List<String> list = new ArrayList<>(set);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
