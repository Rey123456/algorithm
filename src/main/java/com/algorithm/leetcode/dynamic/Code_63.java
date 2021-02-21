package com.algorithm.leetcode.dynamic;

/**
 * @ClassName Code_63
 * @Description Unique Paths II
 * @Author rey
 * @Date 2021/2/20 上午11:33
 */
public class Code_63 {
    public static void main(String[] args){
        System.out.println(new Code_63().uniquePathsWithObstacles1(new int[][]{{0,1},{1,0}}));
        System.out.println(new Code_63().uniquePathsWithObstacles1(new int[][]{{0,1,0},{0,0,0},{0,0,0}}));
        System.out.println(new Code_63().uniquePathsWithObstacles1(new int[][]{{0}}));
        System.out.println(new Code_63().uniquePathsWithObstacles1(new int[][]{{0,0},{1,0}}));
    }
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1) return 0;
        int[][] paths = new int[m][n];
        if(obstacleGrid[0][0]==0) paths[0][0] = 1;
        if(m > 1) {
            if (obstacleGrid[1][0] == 0) paths[1][0] = 1;
            for (int i = 2; i < m; i++) {
                if (obstacleGrid[i][0] == 0 && paths[i - 1][0] == 1) {
                    paths[i][0] = 1;
                } else {
                    paths[i][0] = 0;
                }
            }
        }
        if(n > 1) {
            if (obstacleGrid[0][1] == 0) paths[0][1] = 1;
            for (int i = 2; i < n; i++) {
                if (obstacleGrid[0][i] == 0 && paths[0][i - 1] == 1) {
                    paths[0][i] = 1;
                } else {
                    paths[0][i] = 0;
                }
            }
        }

        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j] == 0){
                    paths[i][j] = paths[i-1][j] + paths[i][j-1];
                }else {
                    paths[i][j] = 0;
                }
            }
        }
        return paths[m-1][n-1];
    }

    //一维数组
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if(obstacleGrid[0][0]==1 || obstacleGrid[m-1][n-1]==1) return 0;
        int[] paths = new int[n];
        paths[0] = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(j == 0){
                    if(obstacleGrid[i][j] == 1) paths[0] = 0;
                }else {
                    if (obstacleGrid[i][j] == 0) {
                        paths[j] += paths[j - 1];
                    } else {
                        paths[j] = 0;
                    }
                }
            }
        }
        return paths[n-1];
    }

}