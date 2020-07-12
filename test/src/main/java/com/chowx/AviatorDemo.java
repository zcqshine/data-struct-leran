package com.chowx;


import com.googlecode.aviator.AviatorEvaluator;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zcqshine on 16/10/25.
 */
public class AviatorDemo {
    public static void test(){
        String firstName = "Congquan";
        String lastName = "Zhou";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("GE148342982183280479310" , NumberUtils.toDouble("50.0",0));
        env.put("GE148342982107880493900", NumberUtils.toDouble("0.0",0));
        String rs = String.valueOf(AviatorEvaluator.execute("GE148342982183280479310/GE148342982107880493900", env));
        System.out.println(rs);
    }



    public static void test2(){
        String conditinon = "1";
        String value = "你好";

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("condition", 1);
        env.put("value",value);
        Object rs =  AviatorEvaluator.execute("condition==condition?value:0+0",env);
        System.out.println(rs);
    }

    public static void test3(){
        String str = "你好=={ni}";
        String regx = "\\{(.*?)\\}";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()) {
            String value = matcher.group(1);
            if(!NumberUtils.isNumber(value)) {
                str = str.replaceAll("\\{|\\}", "'");
            }else {
                str = str.replaceAll("\\{|\\}", "");
            }
        }
        System.out.println(str);
    }

    public static void test4(int dot){
        double str = 3.1415;
//        str = str.substring(0, str.indexOf("."));
        String  str2 = String.format("%."+dot+"f", str);
        System.out.println(str2);
    }

    public static void test5(){
        String firstName = "Congquan";
        String lastName = "Zhou";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("GE148342982183280479310" , 1.00000);
        String rs = String.valueOf(AviatorEvaluator.execute("GE148342982183280479310", env));
        System.out.println(rs);
    }

    public static  void  test6(){
        String str1 = "dfadfa";
        String str2 = "232";

        System.out.println(str1.indexOf(str2));
    }

    public static void test7(){
        String firstName = "";
        String lastName = "Zhou";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("GE148342982183280479310" , firstName);
        AviatorEvaluator.execute("GE148342982183280479310", env);
        String rs = String.valueOf(null);
        System.out.println(rs);
    }


    public static void main(String[] args){
        AviatorDemo.test7();
    }
}
