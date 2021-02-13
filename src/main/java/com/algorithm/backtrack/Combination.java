package com.algorithm.backtrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
回溯法 https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
class Combination {

    /**
     * 给一个不包含重复数字的数组和一个目标数字，找到所有和等于目标数字的排列。比如：
     For example, given candidate set [2, 3, 6, 7] and target 7, A solution set is: [ [7], [2, 2, 3] ]
     https://leetcode.com/problems/combination-sum/
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        backtrackCombinationSum(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }
    private static void backtrackCombinationSum(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, int remain,int start){
        if(remain < 0 ) return;
        else if(remain == 0){   list.add(new ArrayList<>(tempList)); }
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrackCombinationSum(list, tempList, nums,(remain-nums[i]), i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
     给一个包含重复数字的数组和一个目标数字，找到所有和等于目标数字的排列，每个数字不能用多次，但是出现在数组中的可以用。比如：
     For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8, A solution set is: [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
     1 1 2 5 6 7 10
     https://leetcode.com/problems/combination-sum-ii/description/
     */
    public static List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackCombinationSum1(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }
    private static void backtrackCombinationSum1(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, int remain,int start){
        if(remain < 0 ) return;
        else if(remain == 0){   list.add(new ArrayList<>(tempList)); }
        else {
            for (int i = start; i < nums.length; i++) {
                if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
                tempList.add(nums[i]);
                backtrackCombinationSum1(list, tempList, nums,(remain-nums[i]), i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
     Find all possible combinations of k numbers that add up to a number n,
     given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
     Input: k = 3, n = 7 ，Output:[[1,2,4]]
     Input: k = 3, n = 9 ，Output:[[1,2,6], [1,3,5], [2,3,4]]
     https://leetcode.com/problems/combination-sum-iii/
     */
    public static List<List<Integer>> combinationSum2(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        int[] nums = {1,2,3,4,5,6,7,8,9};
        backtrackCombinationSum2(list, new ArrayList<Integer>(), nums, n, k, 0);
        return list;
    }
    private static void backtrackCombinationSum2(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, int remain,int size, int start){
        if(remain < 0 ) return;
        else if(remain == 0 && tempList.size() == size){   list.add(new ArrayList<>(tempList)); }
        else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                backtrackCombinationSum2(list, tempList, nums,(remain-nums[i]), size, i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        /*int[] nums = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> list = combinationSum1(nums, 8);
        for(List<Integer> temp : list){
            System.out.println(temp.toString());
        }*/

        List<List<Integer>> list = combinationSum2(3, 9);
        for(List<Integer> temp : list){
            System.out.println(temp.toString());
        }

    }
}
