package com.algorithm.leetcode;

import java.util.Stack;

/**
 * @ClassName Code_224
 * @Description Basic Calculator
 * @Author rey
 * @Date 2021/2/13 上午10:24
 */
public class Code_224 {
    public static void main(String[] args){
        System.out.println(calculate1("1-(2+3-(4+(5-(1-(2+4-(5+6))))))"));
    }

    /**solution2*****************************************************************************/
    public static int calculate1(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int sign = 1;
        int result = 0;
        int num = 0;
        for(int i=0;i<s.length();i++){
            char charAt = s.charAt(i);
            switch (charAt){
                case '+':
                    result += sign * num;
                    num = 0;
                    sign = 1;
                    break;
                case '-':
                    result += sign * num;
                    num = 0;
                    sign = -1;
                    break;
                case ' ':
                    break;
                case '(':
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                    break;
                case ')':
                    result += sign * num;
                    num = 0;
                    result *= stack.pop();
                    result += stack.pop();
                    break;
                default:
                    num = 10 * num + (charAt - '0');
            }
        }
        result += sign * num;
        return result;
    }

    /**solution1******************************************************************************/
    //全部入栈，遇右括号进行计算
    public static int calculate(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char chartmp : s.toCharArray()){
            switch (chartmp) {
                case ' ':
                    break;
                case ')':
                    String stmp = "";
                    while (stack.peek() != '('){
                        stmp = stack.pop() + stmp;
                    }
                    stack.pop();
                    int restmp = calculateonlynum(stmp);
                    for(char charrestmp : String.valueOf(restmp).toCharArray()){
                        stack.push(charrestmp);
                    }
                    break;
                default:
                    stack.push(chartmp);
            }
        }
        String stmp = "";
        while (!stack.isEmpty()){
            stmp = stack.pop() + stmp;
        }
        if(!stmp.contains("+") && (!stmp.contains("-")||stmp.charAt(0)=='-')) return Integer.valueOf(stmp);
        return calculateonlynum(stmp);
    }


    //只存在+-和数字的计算逻辑
    public static int calculateonlynum(String s) {
        int res = 0;
        boolean addTrue = true;
        String numnow = "0";
        for(char chartmp : s.toCharArray()){
            switch (chartmp) {
                case '+':
                    if(addTrue) res += Integer.valueOf(numnow);
                    else res -= Integer.valueOf(numnow);
                    numnow = "";
                    addTrue = true;
                    break;
                case '-':
                    if(numnow.equals("")) {
                        if(addTrue == false) addTrue = true;
                        else addTrue = false;
                        break;
                    }
                    if(addTrue) res += Integer.valueOf(numnow);
                    else res -= Integer.valueOf(numnow);
                    numnow = "";
                    addTrue = false;
                    break;
                default:
                    numnow = numnow + chartmp;
            }
        }
        if(addTrue) res += Integer.valueOf(numnow);
        else res -= Integer.valueOf(numnow);
        return res;
    }
    /******************************************************************/
}