package com.algorithm.leetcode.dynamic;

/**
 * @ClassName Code_5
 * @Description Longest Palindromic Substring
 * @Author rey
 * @Date 2021/2/18 上午11:35
 */
public class Code_5 {
    public static void main(String[] args){
        System.out.println(new Code_5().longestPalindrome2("ccc"));
    }

    /**中心扩展算法
     * 从每一种边界情况开始「扩展」，边界情况即为子串长度为 1 或 2 的情况
     * */
    public String longestPalindrome2(String s) {
        int start=0,lenmax=0;
        for(int i=0;i<s.length();i++){
            int len1 = expandAroundCenter(s, i, i);//单字符为中间
            int len2 = expandAroundCenter(s, i, i+1);//双相等字符为中间
            int len = Math.max(len1,len2);
            if(len > lenmax){
                /**
                 * 单数： (len-1)/2  **i** i-2
                 * 双数： (len-2)/2  **i(i+1)** i-2
                 * 因为除取整的问题可以选用(len-1)/2
                 * */
                start = i-(len-1)/2;
                lenmax = len;
            }
        }
        return s.substring(start, start+lenmax);
    }

    public int expandAroundCenter(String s, int left, int right){
        while (left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            --left;
            ++right;
        }
        return right-left-1;//(right-1)-(left+1) + 1 = right-left-1
    }

    /**动态规划
     * 状态转移方程
     * (i,j)=P(i+1,j−1)∧(Si==Sj)
     * 临界条件
     * P(i,i)=true
     * P(i,i+1)=(Si==Si+1)
     * ​
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for(int len=0;len<n;len++){ //子字符串的长度
            for(int i=0;i+len<n;i++){ //遍历从当前i为头，长度为len的字符串
                int j = i+len;
                if(len == 0)//当len=0时，单个字符都是true
                    dp[i][j] = true;
                else if(len == 1)//当是两个字符时，相等为true
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                else
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i+1][j-1]);//多个字符时便是两边相等且中间为true
                if(dp[i][j] && len+1 > res.length()){
                    res = s.substring(i, j+1);
                }
            }
        }
        return res;
    }

    //暴力
    public String longestPalindrome(String s) {
        String res = s.substring(0,1);
        for(int j=1;j<s.length();j++){
            char charj = s.charAt(j);
            for(int i=0;i<=j-res.length();i++){
                if(s.charAt(i) == charj){
                    if(isPalindrome(s, i, j)) res = s.substring(i,j+1);
                }
            }
        }
        return res;
    }

    public boolean isPalindrome(String s, int i,int j){
        for(;i<j;i++,j--){
            if(s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }

}