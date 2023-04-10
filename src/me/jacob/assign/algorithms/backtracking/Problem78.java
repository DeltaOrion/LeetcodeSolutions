package me.jacob.assign.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Problem78 {

    /**
     * Given an integer array nums of unique elements, return all possible
     * subsets
     *  (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     */

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> solution = new ArrayList<>();
        result.add(new ArrayList<>(solution));
        findSet(nums,solution,0,0);
        return result;
    }

    public void findSet(int[] nums, List<Integer> solution, int curr, int start) {
        if(curr==nums.length)
            return;

        for(int i = start;i<nums.length;i++) {
            int num = nums[i];
            solution.add(num);
            result.add(new ArrayList<>(solution));
            findSet(nums,solution,curr+1,i+1);
            solution.remove(curr);
        }
    }
}
