package com.algorithm.datasort;
import java.util.Arrays;

/**
 * @Author:
 * @Description: 在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 *
 * @Date: Created in 7:29 PM 8/24/18
 * @Modified by:
 */
public class SelectSort {

    public static void selectSort(int[] num){
        if(num==null || num.length<2){
            return;
        }
        int len = num.length;
        int min;
        for(int i=0; i<len; i++){
            min = i;
            for(int j=i; j<len; j++){
                if(num[j] < num[min]){
                    min = j;
                }
            }
            if(min != i) {
                BubbleSort.swap(num, min, i);
            }
            System.out.println(Arrays.toString(num));
        }
    }

    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        selectSort(arr);
    }
}
