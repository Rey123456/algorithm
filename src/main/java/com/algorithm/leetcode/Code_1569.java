package com.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_1569
 * @Description Number of Ways to Reorder Array to Get Same BST
 *              将子数组重新排序得到同一个二叉查找树的方案数
 * @Author rey
 * @Date 2021/2/13 上午11:42
 */
public class Code_1569 {
    public static void main(String[] args){
        System.out.println(new Code_1569().numOfWays(new int[]{19,3,57,34,15,89,58,35,2,33,46,13,40,79,60,30,61,26,54,22,84,51,75,6,87,44,55,48,27,8,72,47,16,69,36,76,41,1,80,62,73,24,93,50,92,65,39,5,32,67,12,29,90,45,9,38,88,52,10,85,74,66,83,18,20,77,49,28,23,53,86,64,78,82,37,42,56,17,81,4,14,70,59,31,7,25,43,68,91,71,21,63,94,11}));
        //System.out.println(new Code_1569().numOfWays(new int[]{10,23,12,18,4,29,2,8,41,31,25,21,14,35,26,5,19,43,22,37,9,20,44,28,1,39,30,38,36,6,13,16,27,17,34,7,15,3,11,24,42,33,40,32}));
        //System.out.println(new Code_1569().numOfWays(new int[]{31,23,14,24,15,12,25,28,5,35,17,6,9,11,1,27,18,20,2,3,33,10,13,4,7,36,32,29,8,30,26,19,34,22,21,16}));
        //System.out.println(new Code_1569().numOfWays(new int[]{9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18}));
        //System.out.println(new Code_1569().numOfWays(new int[]{3,1,2,5,4,6}));
    }

    /**需要在每一个可能会存在大值的情况下先做下取余以免超过数据范围*/
    public static Integer MOD = 1000000007;
    public int numOfWays(int[] nums) {
        int len = nums.length;
        if(len < 3) return 0;
        ArrayList<Integer> small = new ArrayList<Integer>();
        ArrayList<Integer> large = new ArrayList<Integer>();
        int mid = nums[0];
        for(int i=1;i<len;i++){
            if(nums[i] > mid) large.add(nums[i]);
            else small.add(nums[i]);
        }
        long res = combination(len-1, large.size());
        res = res * numOfWays1(small) % MOD * numOfWays1(large) % MOD;
        return (int)(res-1);
    }

    public long numOfWays1(ArrayList<Integer> nums) {
        int len = nums.size();
        if(len < 3) return 1;
        ArrayList<Integer> small = new ArrayList<Integer>();
        ArrayList<Integer> large = new ArrayList<Integer>();
        int mid = nums.get(0);
        for(int i=1;i<len;i++){
            if(nums.get(i) > mid) large.add(nums.get(i));
            else small.add(nums.get(i));
        }
        long res = combination(len-1, large.size());
        res = res * numOfWays1(small) % MOD * numOfWays1(large) % MOD;
        return res;
    }

    //递归+存储
    public static Map<String,Long> map= new HashMap<String, Long>();
    public static long combination(int m,int n){
        String key= m+","+n;
        if(n==0)
            return 1;
        else if(n==1)
            return m;
        else if(n>m/2)
            return combination(m,m-n);
        else{//n>1
            if(!map.containsKey(key))
                map.put(key, (combination(m-1,n-1)+combination(m-1,n))%MOD);
            return map.get(key);
        }
    }

    //递归计算
    public static long comb(int m,int n){
        if(n==0)
            return 1;
        else if (n==1)
            return m;
        else if(n>m/2)
            return comb(m,m-n);
        else //n>1
            return comb(m-1,n-1)+comb(m-1,n);
    }

}