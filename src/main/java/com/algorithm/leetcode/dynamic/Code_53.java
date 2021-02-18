package com.algorithm.leetcode.dynamic;

/**
 * @ClassName Code_53
 * @Description Maximum Subarray
 * @Author rey
 * @Date 2021/2/17 下午7:31
 */
public class Code_53 {
    public static void main(String[] args){
        //System.out.println(new Code_53().maxSubArray1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(new Code_53().maxSubArray1(new int[]{-2,1}));
    }
    //暴力
    public int maxSubArray(int[] nums) {
        if(nums.length == 1) return nums[0];
        int res = nums[0];
        for(int i=0;i<nums.length;i++){
            int sum = 0;
            for(int j=i;j<nums.length;j++){
                sum += nums[j];
                res = Math.max(sum, res);
            }
        }
        return res;
    }

    /**到当前为止是一个负数且小于当前值，则从当前值开始往后找
     *
     * */
    public int maxSubArray1(int[] nums) {
        if(nums.length == 1) return nums[0];
        int res = nums[0];
        int sum = res;
        for(int i=0,j=1;i<=j && j<nums.length;){
            if(res<0 && res<nums[j]){
                res = nums[j];
                i=j++;
            }else {
                res += nums[j++];
            }
            sum = Math.max(sum, res);
        }
        return sum;
    }

    //f(i)=max{f(i−1)+nums[i],nums[i]}
    public int maxSubArray2(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}