package com.algorithm.leetcode;

/**
 * @ClassName Code_1372
 * @Description TODO
 * @Author rey
 * @Date 2021/2/13 上午10:27
 */
public class Code_1372 {
    public int largestAltitude(int[] gain) {
        int res = 0, tmpres = 0;
        for(int tmp : gain){
            tmpres += tmp;
            if(tmpres > res) res = tmpres;
        }
        return res;
    }
}