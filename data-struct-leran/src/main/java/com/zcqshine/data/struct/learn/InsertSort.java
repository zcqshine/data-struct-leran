package com.zcqshine.data.struct.learn;

import javax.xml.transform.Source;
import java.util.LinkedList;

/**
 * @author zcqshine
 * @date 2019-08-18
 */
public class InsertSort {
    public void insertSort(int[] array){
        int length = array.length;
        for (int i = 1; i < length; i++) {
            int j = i-1;
            int val = array[i];
            for (; j >= 0; j--){
                if (array[j] > val){
                    array[j+1] = array[j];
                } else {
                    break;
                }
            }
            array[j+1] = val; 
        }
    }

    public static void main(String[] args) {
        int[] a = {2,8,1,3,4,5,6,10,9,7};
        InsertSort sort = new InsertSort();
        sort.insertSort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
