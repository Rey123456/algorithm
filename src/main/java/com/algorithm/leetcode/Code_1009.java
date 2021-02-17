package com.algorithm.leetcode;

/**
 * @ClassName Code_1009
 * @Description Complement of Base 10 Integer
 * @Author rey
 * @Date 2021/2/16 上午10:40
 */
public class Code_1009 {
    public static void main(String[] args){
        System.out.print(new Code_1009().bitwiseComplement3(0));
    }
    public int bitwiseComplement(int N) {
        char[] binaryChars = Integer.toBinaryString(N).toCharArray();
        String res = "";
        for(int i=0;i<binaryChars.length;i++) {
            if(binaryChars[i] == '0')
                res += "1";
            else
                res += "0";
        }

        return Integer.parseInt(res, 2);
    }

    //右移 取余算加 1 ：0
    public int bitwiseComplement1(int N) {
        int res = 0;
        int curr = 1;
        do{
            res += (N % 2 == 0? curr : 0);
            curr <<= 1;
            N >>= 1;
        }while (N > 0);
        return res;
    }

    //先计算出比N第一个大的全1的数，然后相减
    public int bitwiseComplement2(int N) {
        int X = 1;
        while (N > X) {
            X = X*2+1;
        }
        return X-N;
    }

    //异或
    public int bitwiseComplement3(int N) {
        int n = N >> 1;
        int o = 1;
        while (n != 0){
            n >>= 1;
            o <<= 1;
            o += 1;
        }
        return N^o;
    }
}