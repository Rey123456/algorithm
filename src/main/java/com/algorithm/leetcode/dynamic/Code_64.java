package com.algorithm.leetcode.dynamic;

/**
 * @ClassName Code_64
 * @Description Minimum Path Sum
 * @Author rey
 * @Date 2021/2/20 下午8:57
 */
public class Code_64 {
    public static void main(String[] args){
        System.out.println(new Code_64().minPathSum1(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
        System.out.println(new Code_64().minPathSum1(new int[][]{{1,2,3},{4,5,6}}));
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] paths = new int[m][n];
        if(m==1 && n==1) return grid[0][0];

        paths[0][0] = grid[0][0];
        if(m > 0){
            for(int i=1;i<m;i++) {
                paths[i][0] = paths[i-1][0] + grid[i][0];
            }
        }
        if(n>0){
            for(int i=1;i<n;i++){
                paths[0][i] = paths[0][i-1] + grid[0][i];
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                paths[i][j] = grid[i][j] + Math.min(paths[i-1][j], paths[i][j-1]);
            }
        }

        return paths[m-1][n-1];
    }

    public int minPathSum1(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if(m==1 && n==1) return grid[0][0];
        int[] paths = new int[n];
        paths[0] = grid[0][0];
        if(n > 0){
            for(int i=1;i<n;i++){
                paths[i] = paths[i-1] + grid[0][i];
            }
        }
        for(int i=1;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if(j==0){
                    paths[j] += grid[i][j];
                }else{
                    paths[j] = grid[i][j] + Math.min(paths[j], paths[j-1]);
                }
            }
        }
        return paths[n-1];
    }
}