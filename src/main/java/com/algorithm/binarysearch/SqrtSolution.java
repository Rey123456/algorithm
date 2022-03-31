package com.algorithm.binarysearch;

public class SqrtSolution {

    /**
     * 利用二分法求平方根
     * */
    public static double sqrtByBSearch(double m, double precision){
        if(m<0) return -1;
        double low = 0;
        double high = m;
        if(m<1){
            low = m;
            high = 1;
        }
        double mid = low + (high-low)/2;
        while (high-low > precision){
            if(mid*mid >m ) high = mid;
            else low = mid;
            mid = low + (high-low)/2;
        }
        return mid;
    }

    /**
     * 利用牛顿迭代法求平方根
     *  x^2-m，斜率为2x
     * */
    public static double sqrtByNewton(double m, double precision){
        double res = (m+m/m)/2;
        while(Math.abs(res*res-m) > precision){
            res = (res+m/res)/2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt(2));
        System.out.println(sqrtByNewton(2, 0.0000001));
    }
}
