package com.algorithm.datasort;
import java.util.Arrays;

/**
 * @Author:
 * @Description: 归并排序是采用分治法的一个非常典型的应用。归并排序的思想就是先递归分解数组，再合并数组。
 *
 * 先考虑合并两个有序数组，基本思路是比较两个数组的最前面的数，谁小就先取谁，取了后相应的指针就往后移一位。
 * 然后再比较，直至一个数组为空，最后把另一个数组的剩余部分复制过来即可。
 *
 * 再考虑递归分解，基本思路是将数组分解成left和right，如果这两个数组内部数据是有序的，那么就可以用上面合并数组的方法将这两个数组合并排序。
 * 如何让这两个数组内部是有序的？可以再二分，直至分解出的小组只含有一个元素时为止，此时认为该小组内部已有序。然后合并排序相邻二个小组即可。
 * @Date: Created in 8:23 PM 8/24/18
 * @Modified by:
 */
public class MergeSort {


    /**
     * temp是为了空间使用是O(n)
     * */
    public static void mergeSort(int[] num,int left,int right, int[] temp){
        if(left < right){
            int mid = (right - left)/2 + left;
            mergeSort(num, left, mid, temp);
            mergeSort(num, mid+1, right, temp);
            mergeArr(num, left, mid, right, temp);
        }
    }

    public static void mergeArr(int[] num, int left, int mid, int right, int[] temp){
        int i = left,j = mid +1, itmp = left;
        while(i<=mid && j<=right){
            if(num[i] <= num[j]){
                temp[itmp] = num[i];
                i++;
            }else {
                temp[itmp] = num[j];
                j++;
            }
            itmp++;
        }
        while(i <= mid){
            temp[itmp] = num[i];
            i++;
            itmp++;
        }
        while (j <= right){
            temp[itmp] = num[j];
            j++;
            itmp++;
        }
        for(int index=left; index<=right;index++){
            num[index] = temp[index];
        }
        System.out.println(Arrays.toString(temp));
    }

    public static void main(String[] args){
        int[] arr = new int[]{4,7,6,5,3,1,2,8};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println(Arrays.toString(arr));
    }
}
