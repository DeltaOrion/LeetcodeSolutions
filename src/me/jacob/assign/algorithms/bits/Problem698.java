package me.jacob.assign.algorithms.bits;

import java.util.*;

public class Problem698 {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (k == 1) {
            return true;
        }

        Arrays.sort(nums);

        if (sum % k != 0 || k > nums.length) {
            return false;
        }

        return backtrack(nums, nums.length - 1, k, 0, sum / k, 0, new HashSet<>());
    }

    private boolean backtrack(int[] nums, int i, int k, int mask, int target, int sum, Set<Integer> DP) {
        if (k == 1) {
            return true;
        }

        if (sum == target) {
            return backtrack(nums, nums.length - 1, k - 1, mask, target, 0, DP);
        }

        if (DP.contains(mask)) {
            return false;
        }

        for (int j = i; j >= 0; j--) {
            if ((mask & (1 << j)) == 0 && sum + nums[j] <= target) {
                if (backtrack(nums, j - 1, k, mask | (1 << j), target, sum + nums[j], DP)) {
                    return true;
                }
            }
        }

        DP.add(mask);
        return false;
    }
}
