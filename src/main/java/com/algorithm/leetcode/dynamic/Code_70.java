package com.algorithm.leetcode.dynamic;

/**
 * @ClassName Code_70
 * @Description Climbing Stairs
 * @Author rey
 * @Date 2021/2/17 下午8:43
 */
public class Code_70 {
    public static void main(String[] args){
        System.out.println(new Code_70().climbStairs(3));
    }

    public int climbStairs(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int first = 1,second = 2;
        for(int i=3;i<=n;i++){
            int tmp = first + second;
            first = second;
            second = tmp;
        }
        return second;
    }

    //
    public int climbStairs2(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        int[] f = new int[n+1];
        f[1] = 1;
        f[2] = 2;
        for(int i=3;i<=n;i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }

    //暴力 Time Limit Exceeded
    public int climbStairs1(int n) {
        if(n == 1) return 1;
        if(n == 2) return 2;
        return climbStairs1(n-1) + climbStairs1(n-2);
    }
}