package com.algorithm.leetcode;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @ClassName Code_1377
 * @Description Frog Position After T Seconds (T 秒后青蛙的位置)
 * @Author rey
 * @Date 2021/2/15 上午11:13
 */
public class Code_1377 {
    public static void main(String[] args){
        // System.out.println(new Code_1377().frogPosition1(8, new int[][]{{2,1},{3,2},{4,1},{5,1},{6,4},{7,1},{8,7}}, 7, 7));
        // System.out.println(new Code_1377().frogPosition1(3, new int[][]{{2,1},{3,2}}, 1, 2));
        // System.out.println(new Code_1377().frogPosition1(1, new int[][]{}, 1, 1));
        // System.out.println(new Code_1377().frogPosition1(82, new int[][]{{2,1},{3,2},{4,2},{5,1},{6,2},{7,2},{8,3},{9,8},{10,6},{11,10},{12,1},{13,1},{14,12},{15,8},{16,3},{17,15},{18,16},{19,17},{20,7},{21,9},{22,9},{23,20},{24,5},{25,10},{26,4},{27,11},{28,8},{29,11},{30,11},{31,7},{32,25},{33,8},{34,27},{35,14},{36,27},{37,9},{38,33},{39,35},{40,6},{41,25},{42,2},{43,25},{44,9},{45,26},{46,23},{47,40},{48,34},{49,26},{50,39},{51,10},{52,47},{53,43},{54,6},{55,49},{56,44},{57,34},{58,15},{59,49},{60,13},{61,32},{62,31},{63,25},{64,50},{65,41},{66,33},{67,2},{68,34},{69,4},{70,49},{71,67},{72,51},{73,19},{74,22},{75,34},{76,13},{77,53},{78,15},{79,62},{80,52},{81,7},{82,63}}, 6, 73));
        System.out.println(new Code_1377().frogPosition1(7, new int[][]{{1,2},{1,3},{1,7},{2,4},{2,6},{3,5}}, 20, 6));
    }
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if(n==1){
            if(target == 1) return 1;
            else return 0;
        }
        HashMap<Integer, HashSet<Integer>> edgeMap = new HashMap<>();//每个数的可走之路，每条路加两次
        for(int i=0;i<edges.length;i++){
            if(!edgeMap.containsKey(edges[i][0])){
                edgeMap.put(edges[i][0], new HashSet<>());
            }
            if(!edgeMap.containsKey(edges[i][1])){
                edgeMap.put(edges[i][1], new HashSet<>());
            }
            edgeMap.get(edges[i][0]).add(edges[i][1]);
            edgeMap.get(edges[i][1]).add(edges[i][0]);
        }

        return findtarget(edgeMap, t, target, 1);

    }

    public double findtarget(HashMap<Integer, HashSet<Integer>> edgeMap, int t,int target,int now){
        if(t < 0) return 0;//步数没有了
        if(now == target && (t==0 || edgeMap.get(now).isEmpty())) return 1;//找到了
        if(edgeMap.get(now).isEmpty()) return 0;//无路可走
        HashSet<Integer> toSet = edgeMap.get(now);//可走之路
        int size = toSet.size();
        for(Integer tmp : edgeMap.get(now)){
            edgeMap.get(tmp).remove(now);//访问过了，去掉
            double res = findtarget(edgeMap, t-1, target, tmp);
            if( res > 0 ){
                return 1.0/size * res;
            }
            edgeMap.get(tmp).add(now);//换一条路
        }
        return 0;
    }


    //就想着给他一个方向
    public double frogPosition1(int n, int[][] edges, int t, int target) {
        if(n == 1) return target==1 ? 1 : 0;
        if(edges.length == 0 && n!=1) return 0;

        int[] childsize = new int[n+1];
        int[] parent = new int[n+1];
        for(int[] tmp : edges){
            if(tmp[0] < tmp[1]){
                childsize[tmp[0]] ++;
                parent[tmp[1]] = tmp[0];
            }else {
                childsize[tmp[1]] ++;
                parent[tmp[0]] = tmp[1];
            }
        }

        int ways = 1;
        int node = target;
        while (t > 0){
            node = parent[node];
            ways *= childsize[node];
            t--;
            if(node == 1){
                break;
            }
        }
        double pos = 0;
        if((node==1 && t==0) ||(node==1 && childsize[target]==0))
            pos = 1.0/ways;
        return pos;
    }
}