package com.algorithm.leetcode;

/**
 * @ClassName Code_66
 * @Description TODO
 * @Author dwj
 * @Date 2021/2/14 下午8:07
 */
public class Code_66 {

    public int[] plusOne(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            digits[i]++;
            if(i!=0 && digits[i] > 9){
                digits[i] = 0;
                continue;
            }else
                break;
        }
        if(digits[0] > 9){
            int[] res = new int[digits.length+1];
            res[0] = 1;
            digits[0] = 0;
            for(int i=0;i<digits.length;i++){
                res[i+1] = digits[i];
            }
            return res;
        }else {
            return digits;
        }
    }

    public int[] plusOne1(int[] digits) {
        for(int i=digits.length-1;i>=0;i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }
}