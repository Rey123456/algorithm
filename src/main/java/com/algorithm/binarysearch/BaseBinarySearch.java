package com.algorithm.binarysearch;

/**
 * @description 二分查找 基本+变形
 * 时间复杂度为O(logn)
 * 需注意
 * 1 循环退出条件
 * 2 mid的取值
 * 3 low和high的更新
 * */
public class BaseBinarySearch {
    /**
     * 基本：非递归
     * */
    public int bsearch(int[] a, int n, int value){
        int low = 0;
        int high = n-1;
        while(low<=high){
            int mid = low + ((high-low)>>1);
            if(a[mid] == value) return mid;
            else if(a[mid] > value) high = mid-1;
            else low = mid+1;
        }
        return -1;
    }

    /**
     * 基本：递归
     * */
    public int bsearchbyRecursion(int[] a, int n, int value){
        return recursion(a, 0, n-1, value);
    }

    public static int recursion(int[] a, int low, int high, int value){
        if(low > high) return -1;
        int mid = low + ((high-low)>>1);
        if(a[mid] == value) return mid;
        else if(a[mid] > value) return recursion(a, low, mid-1, value);
        else return recursion(a, mid+1, high, value);
    }

    /**
     * 变形1：查找第一个值等于给定值的下标
     * mid与value的比较只有三种情况，分这三种情况去判断即可
     * */
    public int getFirstIndex(int[] a, int n, int value){
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>1);
            if(a[mid] > value) high = mid-1;
            else if(a[mid] < value) low = mid+1;
            else{
                if(mid==0 || a[mid-1]!=value) return mid; //是否需要再次向前寻找
                else high = mid-1;
            }
        }
        return -1;
    }

    /**
     * 变形2：查找最后一个值等于给定值的下标
     * */
    public int getLastIndex(int[] a, int n, int value){
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>1);
            if(a[mid] > value) high = mid-1;
            else if(a[mid] < value) low = mid+1;
            else{
                if(mid==n-1 || a[mid+1]!=value) return mid; //是否需要再次向前寻找
                else low = mid+1;
            }
        }
        return -1;
    }

    /**
     * 变形3：查找第一个大于等于给定值的下标
     * */
    public int getFirstMaxIndex(int[] a, int n, int value){
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>1);
            if(a[mid] >= value){
                if(mid==0 || a[mid-1]<value) return mid;
                else high = mid-1;
            }
            else low = mid+1;
        }
        return -1;
    }

    /**
     * 变形4：查找最后一个小于等于给定值的下标
     * */
    public int getLastMinIndex(int[] a, int n, int value){
        int low = 0;
        int high = n-1;
        while (low<=high){
            int mid = low + ((high-low)>>1);
            if(a[mid] > value) high = mid-1;
            else if(a[mid] < value) {
                if(mid==n-1 || a[mid+1]>value) return mid;
                else low = mid+1;
            }
        }
        return -1;
    }

    /**
     * 变形5： 如果有序数组是一个循环有序数组（数组是分段有序的(且假设只有两段)），比如4，5，6，1，2，3。针对这种情况，如何实现一个求“值等于给定值”的二分查找算法呢?
     * 我们发现循环数组存在一个性质:以数组中间点为分区，会将数组分成一个有序数组和一个循环有序数组。
     * 如果首元素小于 mid，说明前半部分是有序的，后半部分是循环有序数组;
     * 如果首元素大于 mid，说明后半部分是有序的，前半部分是循环有序的数组
     * */
    public static int getEqualIndex(int[] a, int n, int value){
        int low = 0;
        int high = n-1;
        if(a[0] == value){
            return 0;
        } else if(a[0] > value) {//说明value只能在后半部分
            while(low <=high) {
                int mid = low + ((high-low)>>1);
                if(a[mid] == value) return mid;
                else if(a[0] > a[mid] && a[mid]<value)  //说明后半部分有序且值在后半分部分中存在
                    return recursion(a, mid+1, high, value);
                else high = mid-1;
            }
        } else { //说明在前半部分
            while(low <=high) {
                int mid = low + ((high-low)>>1);
                if(a[mid] == value) return mid;
                else if(a[0] < a[mid] && a[mid] > value) //说明前半部分有序且值在前半部分
                    return recursion(a, low, mid-1, value);
                else low = mid+1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getEqualIndex(new int[]{6,7,8,10,1,2,3,4,5}, 9, 5));
    }
}
