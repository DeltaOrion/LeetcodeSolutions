package me.jacob.assign.algorithms.array;

public class Problem209 {

    /**
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a
     * subarray
     *  whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     */

    public int minSubArrayLen(int target, int[] nums) {
        int rollingSum = 0;
        int left = 0;
        int result = Integer.MAX_VALUE;

        for(int i=0;i<nums.length;i++) {
            rollingSum += nums[i];
            while(rollingSum >= target) {

                if((i-left+1) < result) {
                    result = i-left+1;
                }
                rollingSum -= nums[left++];
            }
        }

        if(result == Integer.MAX_VALUE)
            return 0;

        return result;
    }
}
