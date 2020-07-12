package com.chowx;


import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author zcqshine
 * @date 2018/2/23
 */
public class Lambda1 {

    public static void main(String[] args) {
        String[] datas = new String[]{"zhou","cong","quan"};
        Arrays.sort(datas, (v1, v2) -> Integer.compare(v1.length(), v2.length()));
        Stream.of(datas).forEach((String param) -> System.out.println(param));
    }

}
