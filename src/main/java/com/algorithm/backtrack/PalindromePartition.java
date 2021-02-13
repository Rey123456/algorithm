package com.algorithm.backtrack;
import java.util.ArrayList;
import java.util.List;

/**
回溯法 https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
class PalindromePartition {

    /**
     * 给一个字符串，将s分成每个子串都是回文的所有排列组合。比如：
     For example, given s = “aab”,Return ：[ [“aa”,”b”], [“a”,”a”,”b”] ]
     https://leetcode.com/problems/palindrome-partitioning/
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrackPartition(list, new ArrayList<String>(), s, 0);
        return list;
    }
    private static void backtrackPartition(List<List<String>> list, List<String> tempList, String s, int start){
        if( start == s.length() ) list.add(new ArrayList<>(tempList));
        else{
            for (int i = start; i < s.length(); i++) {
                if(isPalindrome(s, start, i)){
                    tempList.add(s.substring(start, i+1));
                    backtrackPartition(list, tempList, s, i+1);
                    tempList.remove(s.substring(start, i+1)); //tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
    private static boolean isPalindrome(String s, int low, int high){
        while(low < high){
            if(s.charAt(low++) != s.charAt(high)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> list = partition("aab");
        for(List<String> temp : list){
            System.out.println(temp.toString());
        }

    }
}
