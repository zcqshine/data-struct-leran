package com.chowx;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zcqshine on 16/11/14.
 */
public class Answer {
    private Integer[] array = {8,4,2,1,23,344,12};

    /**
     * 循环
     */
    private void sout(){
        System.out.println("循环输出数列的值:");
        for(int i : array){
            System.out.println(i);
        }
    }

    /**
     * 求和
     */
    private void sum(){
        int sum = 0;
        for (int i : array) {
            sum += i;
        }
        System.out.println("所有数值的和为:" + sum);
    }

    /**
     * 判断键盘输入的值是否存在于数列中
     * @param n
     */
    private void exists(int n){
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(array));
        if(list.contains(n)){
            System.out.println("数列包含输入的值");
        }else{
            System.out.println("数列不包含输入的值");
        }
    }

    private static void replace(){
        String str = "[te{s{}t]";
        System.out.println(str.replaceAll("]", ""));
    }

    public static void main(String[] args){
        Answer.replace();
        String str = "([长度] + [高度]) * [倍数] - [减号] / [除号] > [大于号] < [小于号] == [等号] ";
        String regx = "\\[(.*?)]";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            System.out.println(matcher.group(1));
        }
    }
}
