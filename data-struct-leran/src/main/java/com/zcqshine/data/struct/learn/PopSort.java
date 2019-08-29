package com.zcqshine.data.struct.learn;

import javax.net.ssl.CertPathTrustManagerParameters;

/**
 * @author zcqshine
 * @date 2019-08-19
 */
public class PopSort {
    public void popSort(int[] a){
        for (int i = 0; i<a.length; i++){
            boolean flag = false;
            for (int j = 0; j < a.length-i-1; j++){
                if (a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {10,9,8,6,5,3,4,1,2,7};
        PopSort sort = new PopSort();
        sort.popSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
