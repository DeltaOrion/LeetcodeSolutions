package me.jacob.assign.algorithms.bits;

import java.util.Arrays;

public class Problem1879 {

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int[] DP = new int[1 << nums1.length];
        Arrays.fill(DP, -1);
        return helper(nums1, nums2, DP, (1 << nums1.length) - 1, 0, 0);
    }

    private int helper(int[] nums1, int[] nums2, int[] DP, int mask, int i, int sum) {
        if (mask == 0) {
            return 0;
        }

        if (DP[mask] != -1) {
            return DP[mask];
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < nums2.length; j++) {
            if ((mask & (1 << j)) == 0) {
                min = Math.min(min, helper(nums1, nums2, DP, mask - (1 << j), i + 1, sum + (nums2[j] ^ nums1[i])));
            }
        }

        return DP[mask] = sum + min;
    }
}
