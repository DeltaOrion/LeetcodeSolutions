package me.jacob.assign.algorithms.array;

import java.util.Arrays;

public class Problem2968 {

    public static void main(String[] args) {
        Problem2968 p = new Problem2968();
        int val = p.maxFrequencyScore(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 7, 6, 5, 4, 8, 9, 3, 2, 1, 4, 5, 7, 4, 3, 2, 7, 1, 10, 20, 3, 4, 23, 2, 1, 5, 7, 8, 3, 2}, 5);
        System.out.println(val);
    }

    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int i = 0;
        int j = 1;
        long sumDist = 0;
        int longestRun = 1;
        while(j < nums.length) {
            sumDist = sumDist + nums[j] - nums[j - 1];
            while(sumDist > k && i < j) {
                sumDist = sumDist - nums[i + 1] + nums[i];
                i++;
            }

            longestRun = Math.max(longestRun, j - i + 1);
            j++;
        }

        return longestRun;
    }
}
