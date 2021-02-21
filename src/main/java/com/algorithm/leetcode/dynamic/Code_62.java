package com.algorithm.leetcode.dynamic;

/**
 * @ClassName Code_62
 * @Description Unique Paths
 * @Author rey
 * @Date 2021/2/20 上午10:38
 */
public class Code_62 {
    public static void main(String[] args){
        System.out.println(new Code_62().uniquePaths2(1,2));
        System.out.println(new Code_62().uniquePaths3(3,7));
    }
    //动态规划
    public int uniquePaths(int m, int n) {
        if(m==1 || n==1) return 1;
        int[][] paths = new int[m][n];
        paths[1][0] = 1;
        paths[0][1] = 1;
        for(int i=0;i<m;i++) paths[i][0] = 1;
        for(int i=0;i<n;i++) paths[0][i] = 1;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                paths[i][j] = paths[i-1][j] + paths[i][j-1];
            }
        }
        return paths[m-1][n-1];
    }

    //减少存储
    public int uniquePaths1(int m, int n) {
        int[][] paths = new int[2][n];
        paths[0][0] = 1;
        paths[1][0] = 1;
        for(int i=1;i<n;i++)
            paths[0][i] = 1;
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                paths[i%2][j] = paths[i%2][j-1] + paths[(i-1)%2][j];
            }
        }
        return paths[(m-1)%2][n-1];

    }

    //一维数组
    public int uniquePaths2(int m, int n) {
        int[] paths = new int[n];
        paths[0] = 1;
        for(int i=0;i<m;i++){
            for(int j=1;j<n;j++){
                paths[j] += paths[j-1];
            }
        }
        return paths[n-1];
    }

    //每次不是下就是右，即从(m-1)+(n-1)个总选择中选择(m-1)个向下，(n-1)个向右，即组合结果 ((m-1)+(n-1))!/(m-1)!(n-1)!
    public int uniquePaths3(int m, int n) {
        if(m==1 || n==1) return 1;
        m--;n--;
        if(m<n){
            m = m+n;
            n = m-n;//m
            m = m-n;
        }
        long res= 1;
        for(int i=m+1,j=1;i<=m+n;i++,j++){
            res = res*i/j;
        }
        return (int)res;
    }
}