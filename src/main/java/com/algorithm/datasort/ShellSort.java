package com.algorithm.datasort;
import java.util.Arrays;

/**
 * @Author:
 * @Description: 先将整个待排记录序列分割成为若干子序列分别进行直接插入排序，
 * 待整个序列中的记录基本有序时再对全体记录进行一次直接插入排序
 * @Date: Created in 8:02 PM 8/24/18
 * @Modified by:
 */
public class ShellSort {

    public static void shellSort(int[] num){
        if(num == null || num.length < 2){
            return;
        }
        int len = num.length;
        int gap = len/2;
        while (gap > 0){
            for(int i=gap; i<len; i++){
                int temp = num[i];
                int j = i;
                while(j>=gap && num[j-gap] > temp){
                    num[j] = num[j-gap];
                    j = j - gap;
                }
                num[j] = temp;
            }
            gap = gap/2;
            System.out.println(Arrays.toString(num));
        }
    }
    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        shellSort(arr);
    }
}
