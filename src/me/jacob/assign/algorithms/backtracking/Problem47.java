package me.jacob.assign.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Problem47 {

    /**
     * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
     */

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        permute(new ArrayList<>(),nums,new boolean[nums.length],0);
        return result;
    }

    private void permute(List<Integer> solution, int[] nums, boolean[] used, int curr) {
        if(curr == nums.length)
            result.add(new ArrayList<>(solution));

        boolean[] currUsed = new boolean[21];

        for(int i=0;i<nums.length;i++) {
            if(!used[i] && !currUsed[nums[i]+10]) {
                used[i] = true;
                currUsed[nums[i]+10] = true;
                solution.add(nums[i]);
                permute(solution,nums,used,curr+1);
                solution.remove(curr);
                used[i] = false;
            }
        }
    }
}
