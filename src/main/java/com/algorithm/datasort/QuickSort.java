package com.algorithm.datasort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author:
 * @Description: 快排，也属于交换排序，在每轮挑选一个基准元素pivot，让比它大的排后面，比它小的排前面，从而将数据分成两部分，分治思想
 * 平均复杂度 O(nlogn),最坏情况下O(n^2)(8,7,6,5,4,3,2,1),主要取决于pivot的选择
 * 主要实现方法：挖坑法，指针交换法,指针法在partition中进行的元素交换次数更少
 * @Date: Created in 6:21 PM 8/13/18
 * @Modified by:
 */
public class QuickSort {

    public static int partitionbyhole(int[] arr, int startIndex, int endIndex){
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        int index = startIndex;

        while(right >= left){
            while(right >= left){
                if(arr[right] < pivot){
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            while(right >= left){
                if(arr[left] > pivot){
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        arr[index] = pivot;
        return index;
    }

    public static int partitionbyNeedle(int[] arr, int startIndex, int endIndex){

        //以随机数作为基准元素，并将其与startIndex的数交换
        int index = (int)(startIndex + Math.random()*(endIndex - startIndex));
       // System.out.println(startIndex + " "+ endIndex + " " + index);
        int p = arr[startIndex];
        arr[startIndex] = arr[index];
        arr[index] = p;

        //以第一个位置为基准元素
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;

        while(left != right){
            //控制right指针，比较，左移
            while(left<right && arr[right] > pivot){
                right--;
            }
            //控制left指针，比较，右移
            while(left<right && arr[left] <= pivot){
                left++;
            }
            //交换左右指针所指元素
            if(left < right){
                p = arr[left];
                arr[left] = arr[right];
                arr[right] = p;
            }
        }
        //pivot与指针重合点交换
        p = arr[left];
        arr[left] = arr[startIndex];
        arr[startIndex] = p;

        return left;
    }

    //递归方式
    public static void quickSort(int[] arr, int startIndex, int endIndex){
        //递归结束条件
        if(startIndex >= endIndex){
            return;
        }
        //获得基准元素位置，排完一轮后，基准元素所在位置
       // int pivotIndex = partitionbyhole(arr, startIndex, endIndex);
        int pivotIndex = partitionbyNeedle(arr, startIndex, endIndex);
        // 递归拆分
        quickSort(arr, startIndex,pivotIndex-1);
        quickSort(arr, pivotIndex+1, endIndex);
    }

    //非递归，利用stack
    public static void quickSortwithStack(int[] arr, int startIndex, int endIndex){
        //用一个集合代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<Map<String, Integer>>();

        //整个数列的起止下标，以哈希形式入站
        Map rootParam = new HashMap();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);

        //循环结束条件，栈为空
        while (!quickSortStack.isEmpty()){
            //取栈顶元素起止下标
            Map<String, Integer> param = quickSortStack.pop();
            startIndex = param.get("startIndex");
            endIndex = param.get("endIndex");

            int pivotIndex = partitionbyNeedle(arr, startIndex, endIndex);
            //将基准元素分为两部分，每部分的起止下标入栈
            if(startIndex < pivotIndex - 1){
                Map<String, Integer> leftParam = new HashMap<String, Integer>();
                leftParam.put("startIndex", startIndex);
                leftParam.put("endIndex", pivotIndex-1);
                quickSortStack.push(leftParam);
            }
            if(pivotIndex+1 < endIndex){
                Map<String, Integer> rightParam = new HashMap<String, Integer>();
                rightParam.put("startIndex", pivotIndex+1);
                rightParam.put("endIndex", endIndex);
                quickSortStack.push(rightParam);
            }
        }
    }
    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        quickSortwithStack(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
