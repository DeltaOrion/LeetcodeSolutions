package me.jacob.assign.algorithms.array;

public class Problem213 {

    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
     *
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
     */

    public int rob(int[] nums) {
        if(nums.length==1)
            return nums[0];

        return Math.max(
                rob(nums,0,nums.length-1),
                rob(nums,1,nums.length)
        );
    }

    private int rob(int[] nums, int start, int end) {
        //the sum including i
        int include = 0;
        //the greatest sum excluding the previous value of i
        int exclude = 0;

        for(int i=start;i<end;i++) {
            int oldInc = include;
            //for us to include the current number we needed to
            //exclude the previous number
            include = exclude + nums[i];
            //is it better to include the previous number
            //or to exclude that as well
            exclude = Math.max(oldInc,exclude);
        }

        return Math.max(include,exclude);
    }
}
