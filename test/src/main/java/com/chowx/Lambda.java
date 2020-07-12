package com.chowx;

/**
 * @author zcqshine
 * @date 2018/2/23
 */
public class Lambda {

    public static void printString(String s, Print<String> print) {
        print.print(s);
    }

    public static void main(String[] args) {

        printString("test", System.out::println);

        printString("test", new Print<String>() {
            @Override
            public void print(String x) {
                System.out.println(x);
            }
        });
    }
}
