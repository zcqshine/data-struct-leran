package com.zcqshine.data.struct.learn;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;


/**
 * 快排
 * @author zcqshine
 * @date 2019-08-27
 */
public class QuickSort {
    private int partition(int[] a, int start, int end){
        int pivot = a[end];
        int i = start;
        int j = start;
        while (j < end){
            if (a[j] < pivot){
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
            j++;
        }
        
        int tmp = a[end];
        a[end] = a[i];
        a[i] = tmp;
        return i;
    }
    
    public void sort(int[] a, int start, int end){
        if (start >= end){
            return;
        }
        int pivotIndex = partition(a, start, end);
        sort(a, start, pivotIndex-1);
        sort(a, pivotIndex+1, end);
    }
    
    public void quickSort(int[] a){
        sort(a, 0, a.length-1);
    }
    
    @Test
    public void test(){
        int[] a = {10,6,5,9, 11, 13,12,7,8,9,2,20};
        quickSort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }
    
    @Test
    public void getThird(){
        int[] a = {10,6,5,9, 11, 13,12,7,8,9,2,20};
        Set<Integer> set = new TreeSet<Integer>();
//        Queue<Integer> queue = new LinkedList<Integer>();
        int n = 1;
        while (n < 4){
            int max = a[0];
            for (int i = 0; i<a.length; i++){
                if (max < a[i] && !set.contains(a[i])){
                    max = a[i];
                    System.out.println(max);
                }
                
            }
            set.add(max);
            n++;
        }
        
        
        System.out.println(set.toString());
    }
}
