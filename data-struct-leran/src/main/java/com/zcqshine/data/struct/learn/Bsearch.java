package com.zcqshine.data.struct.learn;

import org.junit.Test;

/**
 * 二分查找
 * @author zcqshine
 * @date 2019-09-04
 */
public class Bsearch {
    
    @Test
    public void bsearch(){
        int val = 15;
        int[] a = {1,2,3,4,5,6,8,10,15,18,100};
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if (a[mid] == val){
                System.out.println(mid);
                return;
            }
            if (a[mid] < val){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
    }

    /**
     * 查找第一个等于给定值的位置
     */
    @Test
    public void findFirstEqValue(){
        int val = 15;
        int[] a = {1,2,3,4,5,6,8,15,15,15,15,15,18,100};
//                 0 1 2 3 4 5 6 7  8   9 10 11 12 13                      
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (a[mid] > val){
                high = mid - 1;
            } else if (a[mid] < val){
                low = mid + 1;
            } else {
                if ( mid == 0 ||  a[mid - 1] != val){
                    System.out.println(mid);
                    return;
                } else {
                    high = mid-1;
                }
            }
        }
    }
    
    @Test
    public void test(){
        System.out.println((8-1)>>1);
    }
}
