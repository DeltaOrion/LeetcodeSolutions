package me.jacob.assign.algorithms.array;

public class Problem334 {

    /**
     * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k
     * and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
     */

    public boolean increasingTriplet(int[] nums) {
        if(nums.length<3) //base case
            return false;

        int small = Integer.MAX_VALUE;
        int big = Integer.MAX_VALUE;

        for(int i=0;i<nums.length;i++) {
            if(nums[i] <= small) {
                //i value
                small = nums[i];
            } else if(nums[i]<=big) {
                //j value
                big = nums[i];
            } else {
                //k value
                return true;
            }
        }
        return false;
    }
}
