package me.jacob.assign.algorithms.array;

public class Problem55 {

    /**
     * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
     *
     * Return true if you can reach the last index, or false otherwise.
     */

    public boolean canJump(int[] nums) {

                /*
        int[] dp = new int[nums.length];
        dp[nums.length-1] = 0;
        for(int i=nums.length-2;i>=0;i--) {
            dp[i] = -1;
            for(int j = 0;j<=nums[i] && i+j < nums.length;j++) {
                if(dp[i+j]!=-1) {
                    if(dp[i] == -1) {
                        dp[i] = dp[i+j]+1;
                    } else {
                        dp[i] = Math.min(dp[i],dp[i+j]+1);
                    }
                }
            }
        }

        return dp[0];

         */
        /*
        int i = 0;
        int jumps = 0;
        while(i<nums.length) {
            int largest = i;
            if(i+nums[i]>=nums.length-1)
                return jumps+1;

            for(int j=1;j<=nums[i] && i+j < nums.length;j++) {
                if(largest == i) {
                    largest = i+j;
                } else {
                    if(nums[i+j] + j >= nums[largest]+(largest-i)) {
                        largest = i+j;
                    }
                }
            }
            i = largest;
            jumps++;
        }
        return jumps;
         */

        int lastVerified = nums.length-1;
        for(int i=nums.length-2;i>=0;i--) {
            if(i+nums[i] >= lastVerified) {
                lastVerified = i;
            }
        }

        return lastVerified == 0;
    }
}
