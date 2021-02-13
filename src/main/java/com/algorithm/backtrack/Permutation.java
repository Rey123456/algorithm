package com.algorithm.backtrack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
回溯法 https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
 */
class Permutation {

    /**
     * 给一个不存在重复数字的数组，找到所有的排列。比如：
     For example,[1,2,3] have the following permutations: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1] ]
     https://leetcode.com/problems/permutations/description/
     */
    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
        backtrackPermute(list, new ArrayList<Integer>(), nums);
        return list;
    }
    private static void backtrackPermute(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums){
        if(tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        }else {
            for (int i = 0; i < nums.length; i++) {
                if(tempList.contains(nums[i])) continue;
                tempList.add(nums[i]);
                backtrackPermute(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /**
     *给一个包含重复数字的数组，找到所有的排列。比如：
     For example, [1,1,2] have the following unique permutations: [ [1,1,2], [1,2,1], [2,1,1] ]
     https://leetcode.com/problems/permutations-ii/description/
     */
    public static List<List<Integer>> permuteWithDup(int[] nums){
        List<List<Integer>> list = new ArrayList<>();
	    Arrays.sort(nums);//如果需要使用i，i-1来去重重复
        //backtrackPermutesWithDup(list, new ArrayList<>(), nums, new Boolean[nums.length]);
        backtrackPermutesWithDup2(list, new ArrayList<Integer>(), nums);
        return list;
    }
    private static void backtrackPermutesWithDup(List<List<Integer>> list, List<Integer> tempList, int[] nums, Boolean[] used){//借助标记数组
        if(tempList.size() == nums.length && !list.contains(tempList)) {  //符合条件的存下来
            list.add(new ArrayList<>(tempList));
        }else {
            for (int i = 0; i < nums.length; i++) {
                if(used[i] != null && used[i]) continue;
                tempList.add(nums[i]);
                used[i] = true;
                backtrackPermutesWithDup(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    private static void backtrackPermutesWithDup1(List<List<Integer>> list, List<Integer> tempList, int[] nums, Boolean[] used){
        if(tempList.size() == nums.length) {  //符合条件的存下来
            list.add(new ArrayList<>(tempList));
        }else {
            for (int i = 0; i < nums.length; i++) {
                if(used[i] != null && used[i] || i > 0 && nums[i] == nums[i-1] && used[i-1]) continue;
                tempList.add(nums[i]);
                used[i] = true;
                backtrackPermutesWithDup1(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    private static void backtrackPermutesWithDup2(List<List<Integer>> list, List<Integer> tempList, int[] nums){//记录nums的下标
        if(tempList.size() == nums.length) {  //符合条件的存下来
            List<Integer> temp = new ArrayList<>();
            for(int i:tempList){
                temp.add(nums[i]);
            }
            list.add(new ArrayList<>(temp));
        }else {
            for (int i = 0; i < nums.length; i++) {
                if(tempList.contains(i) || i > 0 && nums[i] == nums[i-1] && tempList.contains(i-1)) continue;
                tempList.add(i);
                backtrackPermutesWithDup2(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        /*int[] nums = {1,2,3};
        List<List<Integer>> list = permute(nums);
        for(List<Integer> temp : list){
            System.out.println(temp.toString());
        }*/

        int[] nums = {1,1,2};
        List<List<Integer>> list = permuteWithDup(nums);
        for(List<Integer> temp : list){
            System.out.println(temp.toString());
        }
    }
}
