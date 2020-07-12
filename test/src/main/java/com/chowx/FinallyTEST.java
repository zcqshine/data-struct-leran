package com.chowx;

/**
 * Created by zcqshine on 2017/3/22.
 */
public class FinallyTEST {
    public int test(){
        try {
            System.out.println("this is return");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("this is finally test");
            return 3;
        }
    }

    public static void main(String[] args) {
//        FinallyTEST test = new FinallyTEST();
//        int i = test.test();
//        System.out.println("i = " + i);
        String str1 = "asdfasdfadsfadfadfadfadsfz中文adsfasdfasdfasdfadsfadsf";
        String str2 = "asdfasdfadsfadfadfadfadsfadsfasdfasdfasdfadsfadsfsfafasfasf23412 ";
        long start = System.currentTimeMillis();
        System.out.println(FinallyTEST.isEqual(str1,str2));;
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
    public static boolean isEqual(String str1, String str2) {

        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chas1 = str1.toCharArray();
        char[] chas2 = str2.toCharArray();
        int[] map = new int[65535];
        for (int i = 0; i < chas1.length; i++) {
            map[chas1[i]]++;
        }
        for (int i = 0; i < chas2.length; i++) {
            if (map[chas2[i]]-- == 0) {
                return false;
            }
        }

        return true;
    }
}
