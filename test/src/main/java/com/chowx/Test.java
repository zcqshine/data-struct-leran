package com.chowx;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zcqshine on 16/10/31.
 */
public class Test {
    static int i = 1;
    public static void main(String[] args){
        String str = "[长度] + [高度]) * [倍数] - [减号] / [除号] > [大于号] < [小于号] == [等号]";

//        str = str.replaceAll("\\[", ",\\[");
//        str = str.replaceAll("\\]", "\\],");
//        if(str.indexOf(",") == 0){
//            str = str.replaceFirst(",", "");
//        }
//        System.out.println(str.lastIndexOf(","));
//        System.out.println(str.length());
//        if(str.lastIndexOf(",") == str.length()-1) {
//
//            str = str.substring(0, str.length() - 1);
//        }
//        System.out.println(str);

//        String regx = "\\[(.*?)\\]";
//        Pattern pattern = Pattern.compile(regx);
//        Matcher matcher = pattern.matcher(str);
//
//        int i = 0;
//        while(matcher.find()){
//            String rs = matcher.group(1);
//            System.out.println(i + ":" + rs);
//            i++;
//        }

//        String regx2 = "\\[|\\]";
//
//        str = str.replaceAll(regx2,"");
//        System.out.println(str);

//        Test test = new Test();
//        test.change(1);
//        System.out.println(i);

        Integer numCounter = NumCounter.NUM_COUNTER.getCounter();
        System.out.println(numCounter);
        Integer numCounter2 = NumCounter.NUM_COUNTER.getCounter();
        System.out.println(numCounter2);

        NumCounter.NUM_COUNTER.getCounter();
        NumCounter.NUM_COUNTER.getCounter();

    }

    public void change(Integer i){
        i = 100;
    }

}
