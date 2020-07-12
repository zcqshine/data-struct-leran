package com.chowx;

import java.util.regex.Pattern;

/**
 * @author zcqshine
 * @date 2018/6/21
 */
public class RegTest {

  public static void main(String[] args) {
    String reg = "[0-4]{1}";
    System.out.println(Pattern.matches(reg, "12"));
    System.out.println(Pattern.matches(reg, "0"));
  }




}
