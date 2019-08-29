package com.zcqshine.data.struct.learn;

import javax.xml.transform.Source;

/**
 * @author zcqshine
 * @date 2019-08-23
 */
public class MergeSort {
    public void mergerSort(int[] array, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (start+end)/2;
        mergerSort(array, start, mid);
        mergerSort(array, mid+1, end);
        merge(array, start, mid, end);
    }
    
    private void merge(int[] a, int left, int mid, int right){
        System.out.println("a.legth="+a.length + ", right-left + 1 = " + (right-left+1));
        int[] tmp = new int[right-left+1];
        int p1 = left, p2 = mid+1, k = 0;
        
        while (p1 <= mid && p2 <= right){
            if (a[p1] < a[p2]){
                tmp[k++] = a[p1++];
            } else {
                tmp[k++] = a[p2++];
            }
        }
        
        while (p1 <= mid){
            tmp[k++] = a[p1++];
        }
        while (p2 <= right){
            tmp[k++] = a[p2++];
        }
        
        for (int i = 0; i < tmp.length; i++){
            a[i+left] = tmp[i];
        }
    }

    public static void main(String[] args) {
        MergeSort m = new MergeSort();
        int[] a = {10,6,5,9, 11, 13,12,7,8,9,2,20};
        m.mergerSort(a, 0, a.length-1);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
