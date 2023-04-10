package me.jacob.assign.algorithms.array;

public class Problem45 {
    /**
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     *
     * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
     *
     * 0 <= j <= nums[i] and
     * i + j < n
     * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
     */

    public int jump(int[] nums) {
        int farthestPoint = 0;
        int jumps = 0;
        int currEnd = 0;

        for(int i=0;i<nums.length-1;i++) {
            farthestPoint = Math.max(nums[i]+i,farthestPoint);
            if(i==currEnd) {
                currEnd = farthestPoint;
                jumps++;
            }
        }

        return jumps;
    }

}
