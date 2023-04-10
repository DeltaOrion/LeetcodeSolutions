package me.jacob.assign.algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem40 {

    /*
    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
    Each number in candidates may only be used once in the combination.
    Note: The solution set must not contain duplicate combinations.
     */

    private final List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<Integer> solution = new ArrayList<>();
        target = target;
        findCombSum(candidates,solution,target,0);
        return result;
    }


    public void findCombSum(int[] nums, List<Integer> solution, int target, int start) {
        for(int i = start;i<nums.length;i++) {
            if(i==start || nums[i] != nums[i-1]) {
                int num = nums[i];
                if(target-num == 0) {
                    solution.add(num);
                    result.add(new ArrayList<>(solution));
                    solution.remove(solution.size()-1);
                } else if(target - num > 0) {
                    solution.add(num);
                    findCombSum(nums,solution,target-num,i+1);
                    solution.remove(solution.size()-1);
                }
            }
        }
    }
}
