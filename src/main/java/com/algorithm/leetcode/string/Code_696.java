package com.algorithm.leetcode.string;

/**
 * @ClassName Code_696
 * @Description Count Binary Substrings
 * @Author rey
 * @Date 2021/2/21 下午7:41
 */
public class Code_696 {
    public static void main(String[] args){
        System.out.println(new Code_696().countBinarySubstrings2("00110011"));
        System.out.println(new Code_696().countBinarySubstrings2("10101"));
    }
    public int countBinarySubstrings(String s) {
        if(!s.contains("0") || !s.contains("1")) return 0;
        int[] num01 = new int[2];
        int res = 0;
        for(int i=0;i<s.length();i++){
            if(i != 0) {
                if(s.charAt(i) != s.charAt(i-1)){
                    if(num01[0]!=0 && num01[1]!=0){
                        res += Math.min(num01[0], num01[1]);
                        num01[s.charAt(i)-'0'] = 0;
                    }
                }
            }
            num01[s.charAt(i)-'0']++;
        }
        if(num01[0]!=0 && num01[1]!=0){
            res += Math.min(num01[0], num01[1]);
        }
        return res;
    }

    public int countBinarySubstrings1(String s) {
        if(!s.contains("0") || !s.contains("1")) return 0;
        int[] num01 = new int[2];
        int res = 0;
        char[] chars = s.toCharArray();
        char last = '2';
        for(int i=0;i<s.length();i++){
            if(chars[i] != last){
                if(num01[0]!=0 && num01[1]!=0){
                    res += Math.min(num01[0], num01[1]);
                    num01[chars[i]-'0'] = 0;
                }
            }

            num01[chars[i]-'0']++;
            last = chars[i];
        }
        if(num01[0]!=0 && num01[1]!=0){
            res += Math.min(num01[0], num01[1]);
        }
        return res;
    }

    public int countBinarySubstrings2(String s) {
        if(!s.contains("0") || !s.contains("1")) return 0;
        int last=0,res = 0;
        for(int i=0;i<s.length();){
            char c = s.charAt(i);
            int count = 0;
            while (i<s.length() && s.charAt(i)==c){
                count++;
                i++;
            }
            res += Math.min(count, last);
            last = count;
        }

        return res;
    }
}