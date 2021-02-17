package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * @ClassName Code_561
 * @Description  Array Partition I
 * @Author rey
 * @Date 2021/2/15 下午5:22
 */
public class Code_561 {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for(int i=0;i<nums.length;i+=2){
            res += nums[i];
        }
        return res;
    }

    public int arrayPairSum1(int[] nums) {
        int res = 0;

        return res;
    }
}