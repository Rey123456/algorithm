package com.algorithm.datasort;

import java.util.Arrays;

/**
 * @Author:
 * @Description: 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对第0个到第n-1个数据做同样的工作。这时，最大的数就“浮”到了数组最后的位置上。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * 优化1 bubbleSort1 某一趟遍历如果没有数据交换，则说明已经排好序了，因此不用再进行迭代了。用一个标记记录这个状态即可
 * 优化2 bubbleSort2 记录某次遍历时最后发生数据交换的位置，这个位置之后的数据显然已经有序，不用再排序了。
 * 因此通过记录最后发生数据交换的位置就可以确定下次循环的范围了。
 * @Date: Created in 6:38 PM 8/24/18
 * @Modified by:
 */
public class BubbleSort {

    public static void swap(int[] num, int i, int j){
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    /**
     *
     * */
    public static void bubbleSort(int[] num){
        if(num==null || num.length<2){
            return;
        }
        int len = num.length;
        /**
         * 每次轮一遍将最大值放在最下面
         * */
        /*for(int i=0; i< len-1; i++){
            for(int j=0; j< len-1-i; j++){
                if(num[j] > num[j+1]){
                    swap(num, j, j+1);
                }
            }
            System.out.println(Arrays.toString(num));
        }*/

        /**
         * 每次将最小值放在最上面
         * */
        for(int i=0;i<len-1;i++){
            for(int j=len-1;j>i;j--){
                if(num[j] < num[j-1])
                    swap(num, j,j-1);
            }
            System.out.println(Arrays.toString(num));
        }
    }

    public static void bubbleSort1(int[] num){
        if(num==null || num.length<2){
            return;
        }
        int len = num.length;
        boolean isSwap = false;
        for(int i=0;i<len-1;i++){
            for(int j=len-1;j>i;j--){
                if(num[j] < num[j-1]) {
                    swap(num, j, j - 1);
                    isSwap = true;
                }
            }
            if(!isSwap){
                return;
            }
            isSwap = false;
            System.out.println(Arrays.toString(num));
        }
    }

    public static void bubbleSort2(int[] num){
        if(num==null || num.length<2){
            return;
        }
        int len = num.length;
        boolean isSwap = false;
        int index = len -1;
        for(int i=0;i<len-1;i++){
            for(int j=0; j< index; j++){
                if(num[j] > num[j+1]){
                    swap(num, j, j+1);
                    isSwap = true;
                }
                if(j == index-1 && isSwap){
                    index = j;
                }
            }
            if(!isSwap){
                return;
            }
            isSwap = false;
            System.out.println(Arrays.toString(num));
        }
    }

    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        bubbleSort2(arr);
       // System.out.println(Arrays.toString(arr));
    }
}
