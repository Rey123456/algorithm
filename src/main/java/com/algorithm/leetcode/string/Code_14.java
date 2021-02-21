package com.algorithm.leetcode.string;

/**
 * @ClassName Code_14
 * @Description Longest Common Prefix
 * @Author rey
 * @Date 2021/2/21 下午3:06
 */
public class Code_14 {
    public static void main(String[] args){
        //System.out.println(new Code_14().longestCommonPrefix(new String[]{"flower","flow","flight"}));
        //System.out.println(new Code_14().longestCommonPrefix(new String[]{"ab", "a"}));
        System.out.println(new Code_14().longestCommonPrefix(new String[]{"reflower","flow","flight"}));
    }

    //横向挨个比较
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        String res = strs[0];
        for(int i=1;i<strs.length;i++){
            int len = Math.min(res.length(), strs[i].length());
            for(int j=0;j<len;j++){
                if(res.charAt(j) != strs[i].charAt(j)){
                    res = res.substring(0,j);
                    break;
                }
            }
            if(len < res.length()) {
                res = res.substring(0, len);
            }
            if(res.length() == 0){
                return "";
            }
        }
        return res;
    }

    public String longestCommonPrefix1(String[] strs) {
        if(strs.length == 0) return "";
        String res = strs[0];
        for(int i=1;i<strs.length;i++){
            while (!strs[i].startsWith(res))
                res = res.substring(0, res.length()-1);
        }
        return res;
    }
}