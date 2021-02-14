package com.algorithm.leetcode;

import java.util.HashMap;

/**
 * @ClassName Code_525
 * @Description Contiguous Array 连续数组
 * @Author rey
 * @Date 2021/2/14 下午3:58
 */
public class Code_525 {
    public static void main(String[] args){
        System.out.println(new Code_525().findMaxLength1(new int[]{0,1,1,0,1,1,1,0}));
    }

    //暴力法
    public int findMaxLength(int[] nums) {
        int res = 0;
        if(nums.length < 2) return res;
        for(int i=0;i<nums.length;i++){
            int[] num01 = new int[]{0,0};
            for(int j=i;j<nums.length;j++){
                num01[nums[j]]++;
                if(num01[0] == num01[1] && j-i+1>res)//相同
                    res = j-i+1;
            }
        }
        return res;
    }

    /**
     我们使用一个变量 count ，用来保存遍历数组过程中到目前为止遇到的 0 和 1 的相对数量。
     遇到 1 的时候， count+1 ，遇到 0 的时候 count-1 。

     我们从头开始遍历数组，在任何时候，如果count 变成了 0 ，这表示我们从开头到当前位置遇到的 0 和 1 的数目一样多。
     另一个需要注意的是，如果我们在遍历数组的过程汇中遇到了相同的 count 2 次，这意味着这两个位置之间 0 和 1 的数目一样多。
     * */
    public int findMaxLength1(int[] nums) {
        int res = 0, count=0;
        if(nums.length < 2) return res;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        countMap.put(0,-1);
        for(int i=0;i<nums.length;i++){
            count = count + (nums[i]==0?-1:+1);
            if(countMap.containsKey(count)){
                res = Math.max(res, i-countMap.get(count));
            }else {
                countMap.put(count, i);
            }
        }
        return res;
    }
}