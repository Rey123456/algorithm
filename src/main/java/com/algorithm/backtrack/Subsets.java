package com.algorithm.backtrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
回溯法 https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
class Subsets {

    /**
     * 给一个不存在重复数字的数组，找到所有可能的子数组。比如：
     If nums = [1,2,3], a solution is:[ [3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[] ]
     https://leetcode.com/problems/subsets/description/
     */
    public static List<List<Integer>> subsets(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackSubsets(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    private static void backtrackSubsets(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrackSubsets(list, tempList, nums, i+1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     *给一个包含重复数字的数组，找到所有可能的子数组。比如：
     If nums = [1,2,2], a solution is:[ [2],[1],[1,2,2],[2,2],[1,2],[] ]
     https://leetcode.com/problems/subsets-ii/description/
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrackSubsetsWithDup(list, new ArrayList<Integer>(), nums, 0);
        return list;
    }
    private static void backtrackSubsetsWithDup(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start){
        /*if(!list.contains(tempList)) {  //符合条件的存下来
            list.add(new ArrayList<>(tempList));
        }*/
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; //前一个数经历过了相等的下一个值就不用经历了，关键在已经排过序
            tempList.add(nums[i]);
            backtrackSubsetsWithDup(list, tempList, nums, i+1);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        /*int[] nums = {1,2,3};
        List<List<Integer>> list = subsets(nums);
        for(List<Integer> temp : list){
            System.out.println(temp.toString());
        }*/

        int[] nums = {1,2,2};
        List<List<Integer>> list = subsetsWithDup(nums);
        for(List<Integer> temp : list){
            System.out.println(temp.toString());
        }
    }
}
