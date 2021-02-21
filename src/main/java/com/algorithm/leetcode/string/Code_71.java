package com.algorithm.leetcode.string;

import java.util.Stack;

/**
 * @ClassName Code_71
 * @Description Simplify Path
 * @Author rey
 * @Date 2021/2/21 下午4:34
 */
public class Code_71 { //String Stack

    public static void main(String[] args){
        System.out.println(new Code_71().simplifyPath("/a/./b/../../c/"));
        System.out.println(new Code_71().simplifyPath("/../"));
    }
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        for(String str : path.split("/")){
            switch (str){
                case "":
                case ".":
                    break;
                case "..":
                    if(!stack.empty()) stack.pop();
                    break;
                default:
                    stack.push(str);
            }
        }
        StringBuilder res = new StringBuilder();
        for(int i=0;i<stack.size();i++){
            res.append("/").append(stack.get(i));
        }
        return res.length()==0?"/":res.toString();
    }
}