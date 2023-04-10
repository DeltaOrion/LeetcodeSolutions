package me.jacob.assign.algorithms.array;

public class Problem136 {

    /**
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     *
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     */

    public int singleNumber(int[] nums) {
        int result = nums[0];
        for(int i=1;i<nums.length;i++) {
            result = result ^ nums[i];
        }

        return result;
    }
}
