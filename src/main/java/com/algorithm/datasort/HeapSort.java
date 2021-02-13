package com.algorithm.datasort;
import java.util.Arrays;

/**
 * @Author:
 * @Description: 堆排序在 top K 问题中使用比较频繁。堆排序是采用二叉堆的数据结构来实现的，虽然实质上还是一维数组。二叉堆是一个近似完全二叉树 。
 * 二叉堆具有以下性质：
 * 父节点的键值总是大于或等于（小于或等于）任何一个子节点的键值。
 * 每个节点的左右子树都是一个二叉堆（都是最大堆或最小堆）。
 *
 * @Date: Created in 5:38 PM 8/27/18
 * @Modified by:
 */
public class HeapSort {
    public static void heapSort(int[] arr){
        firstMinHeap(arr);

        for(int i=arr.length-1;i>0;i--){
            //头不变才能利用minHeapFix调整堆，所以不断让跟去和最后一个叶结点交换
            BubbleSort.swap(arr, 0,i);
            minHeapFix(arr, 0, i);
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void firstMinHeap(int[] arr){
        //第一次构建小顶堆
        for(int i=(arr.length-1)/2; i>=0; i--){
            minHeapFix(arr, i, arr.length-1);
        }
        System.out.println(Arrays.toString(arr));
    }

    //子节点左面是2i，右面是2i+1
    public static void minHeapFix(int[] arr, int i,int n){
        int j = 2*i + 1;

        while(j < n){
            if(j+1<n && arr[j+1] < arr[j]){
                j++;
            }
            if(arr[i] <= arr[j])
                break;
            BubbleSort.swap(arr, i, j);

            i = j;
            j = 2*i+1;
        }
    }


    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        heapSort(arr);
        // System.out.println(Arrays.toString(arr));
    }
}
