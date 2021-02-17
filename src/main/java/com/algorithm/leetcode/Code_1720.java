package com.algorithm.leetcode;

/**
 * @ClassName Code_1720
 * @Description Decode XORed Array 解码异或后的数组
 * @Author rey
 * @Date 2021/2/15 下午4:28
 */
public class Code_1720 {
    public static void main(String[] args){
        for(int tmp : new Code_1720().decode(new int[]{6}, 1)){
            System.out.print(tmp + " ");
        }
    }

    //异或的逆运算还是异或
    public int[] decode(int[] encoded, int first) {
        int[] res = new int[encoded.length + 1];
        res[0] = first;
        for(int i=0;i<encoded.length;i++){
            res[i+1] = encoded[i] ^ res[i];
        }
        return res;
    }
}