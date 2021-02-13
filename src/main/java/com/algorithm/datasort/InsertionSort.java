package com.algorithm.datasort;
import java.util.Arrays;

/**
 * @Author:
 * @Description: 从第一个元素开始，该元素可以认为已经被排序
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 如果被扫描的元素（已排序）大于新元素，将该元素后移一位
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
 * 将新元素插入到该位置后
 * @Date: Created in 7:41 PM 8/24/18
 * @Modified by:
 */
public class InsertionSort {
    public static void insertionSort(int[] num){
        if(num==null || num.length<2){
            return;
        }
        int len = num.length;
        int index = 0;
        for(int i=1; i<len; i++){
            int temp = num[i];
            index = 0;
            for(int j=i; j>0; j--){
                if(temp < num[j-1]){
                    num[j] = num[j-1];
                }else {
                    index = j;
                    break;
                }
            }
            if(index != i) {
                num[index] = temp;
            }
            System.out.println(Arrays.toString(num));
        }
    }

    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        insertionSort(arr);
    }
}
