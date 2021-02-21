package com.algorithm.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Code_3
 * @Description Longest Substring Without Repeating Characters
 * @Author rey
 * @Date 2021/2/21 上午10:31
 */
public class Code_3 {
    public static void main(String[] args){
        System.out.println(new Code_3().lengthOfLongestSubstring1("pwwkew"));
    }

    /**
     * 窗口函数，利用set获得当下不重复的值，当存在重复则向后移动
     * */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 2) return s.length();
        char[] chars = s.toCharArray();
        int len=1;
        HashSet<Character> characterHashSet = new HashSet<>();
        characterHashSet.add(chars[0]);
        for(int i=0,j=1;j<s.length();){
            if(!characterHashSet.contains(chars[j])){
                characterHashSet.add(chars[j++]);
            }else{
                len = Math.max(j-i, len);
                characterHashSet.remove(chars[i++]);
            }
        }
        len = Math.max(characterHashSet.size(), len);
        return len;
    }

    /**
     * 利用i，j分别表示左右两个指向，map存储每个字符的下标，当遇到在map中的字符，i向右移动相应位置
     * */
    public int lengthOfLongestSubstring1(String s) {
        if(s.length() < 2) return s.length();
        int len=0;
        HashMap<Character,Integer> charMap = new HashMap<>();
        for(int i=0,j=0;j<s.length();j++){
            if(charMap.containsKey(s.charAt(j))){
                i = Math.max(i, charMap.get(s.charAt(j))+1);
            }
            charMap.put(s.charAt(j), j);
            len = Math.max(len, j-i+1);
        }
        return len;
    }

    /**
     * 不使用map而使用字符遍历来移动left下标
     * */
    public int lengthOfLongestSubstring2(String s) {
        if(s.length() < 2) return s.length();
        int len=1,left=0;
        for(int i=0;i<s.length();i++){
            for(int j=left;j<i;j++){
                if(s.charAt(j) == s.charAt(i)){
                    left = j+1;
                    break;
                }
            }
            len = Math.max(len, i-left+1);
        }
        return len;
    }
}