package com.algorithm.leetcode;

import java.util.Stack;

/**
 * @ClassName Code_1003
 * @Description Check If Word Is Valid After Substitutions 检查替换后的词是否有效
 * @Author rey
 * @Date 2021/2/16 下午8:27
 */
public class Code_1003 {
    public static void main(String[] args){
        System.out.println(new Code_1003().isValid2("ababcc"));
        //System.out.println(new Code_1003().isValid1("abccba"));
    }
    public boolean isValid(String s) {
        int len = s.length();
        while (len > 0){
            s = s.replaceAll("abc", "");
            if(s.length() == len && len > 0) return false;
            len = s.length();
        }
        return true;
    }

    //使用栈记录前面的数据，遇到c表示当前必须是连着的abc
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<>();
        for(char tmp : s.toCharArray()){
            if(tmp == 'c'){
                if(stack.size() < 2)
                    return false;
                if(stack.pop()!='b')
                    return false;
                if(stack.pop()!='a')
                    return false;
            }else {
                stack.push(tmp);
            }
        }
        if(!stack.isEmpty())
            return false;
        return true;
    }

    int index;
    public boolean isValid2(String s){
        if(s.length() < 3) return false;
        char[] charArray = s.toCharArray();
        index = 0;
        return isValid3(charArray);
    }
    public boolean isValid3(char[] charArray){
        for(int i=0;i<3;i++){
            if(index == charArray.length) return false;//总长度不是3的倍数
            if(charArray[index] == ('a'+i))
                index++;
            else if(charArray[index] == 'a') {
                isValid3(charArray);
                i--;
            }
            else
                return false;
        }
        if(index < charArray.length)
            return isValid3(charArray);
        return true;
    }




}