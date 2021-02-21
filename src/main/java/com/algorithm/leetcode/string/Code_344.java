package com.algorithm.leetcode.string;

/**
 * @ClassName Code_344
 * @Description   Reverse String
 * @Author rey
 * @Date 2021/2/21 上午10:20
 */
public class Code_344 {
    public static void main(String[] args){
        new Code_344().reverseString(new char[]{'h','e','l','l','o'});
    }
    public void reverseString(char[] s) {
        for(int i=0,j=s.length-1;i<j;i++,j--){
            char tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;
        }
    }
}