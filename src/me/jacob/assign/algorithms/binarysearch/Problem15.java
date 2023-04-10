package me.jacob.assign.algorithms.binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem15 {

    /**
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     *
     * Notice that the solution set must not contain duplicate triplets.
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> solution = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) // pass duplicates for i
                continue;
            int j = i + 1;
            int k = nums.length - 1;
            int target = -nums[i];
            while (j < k) {
                if (j > i + 1 && nums[j] == nums[j - 1]) { // pass duplicates for j
                    j++;
                    continue;
                }
                if (k < nums.length - 1 && nums[k] == nums[k+1]) { // pass duplicates for k
                    k--;
                    continue;
                }
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);
                    solution.add(triplet);
                } if (nums[j] + nums[k] < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return solution;
    }
}
