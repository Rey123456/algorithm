package com.algorithm.leetcode;

import java.util.Arrays;

/**
 * @ClassName Code_1288
 * @Description Remove Covered Intervals  删除被覆盖区间
 * @Author rey
 * @Date 2021/2/13 下午8:59
 */
public class Code_1288 {
    public static void main(String[] args){
        System.out.println(new Code_1288().removeCoveredIntervals2(new int[][]{{1,6},{4,7},{4,8}}));
        //System.out.println(new Code_1288().removeCoveredIntervals1(new int[][]{{34335,39239},{15875,91969},{29673,66453},{53548,69161},{40618,93111}}));
    }

    //两个for循环
    public int removeCoveredIntervals(int[][] intervals) {
        int res = intervals.length;
        for(int i=0;i<intervals.length;i++){
            for(int j=0;j<intervals.length;j++){
                if(i!=j && intervals[i][0]>=intervals[j][0] && intervals[i][1]<=intervals[j][1]) {
                    res--;
                    break;
                }
            }
        }
        return res;
    }

    //只排第一列,如果左侧相等，那么必定有一方盖住另一方，所以只留一个，即在此需判断v[0] > left && v[1] > right
    public int removeCoveredIntervals1(int[][] intervals) {
        int res = 0, left = -1, right = -1;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for (int[] v : intervals) {
            if (v[0] > left && v[1] > right) {
                left = v[0];
                ++res;
            }
            right = Math.max(right, v[1]);
        }
        return res;
    }

    //排序时第一列升序，第一列相同时第二列降序；只需比较右侧值需一直增大
    public int removeCoveredIntervals2(int[][] intervals) {
        int res = 0, right = -1;
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0]?b[1]-a[1]:a[0]-b[0]));
        for (int[] v : intervals) {
            if (v[1] > right) {
                right = v[1];
                ++res;
            }
        }
        return res;
    }
}