package me.jacob.assign.algorithms.dp;

import java.util.ArrayList;
import java.util.List;

public class Problem300 {

    /**
     * Given an integer array nums, return the length of the longest strictly increasing
     * subsequence
     * .
     */
    public int lengthOfLIS(int[] nums) {
        /*
        int[] DP = new int[nums.length];
        DP[0] = 1;
        int maxDP = 1;
        for(int i=1;i<nums.length;i++) {
            int max = 0;
            for(int j=0;j<i;j++) {
                if(nums[j] < nums[i] && DP[j] > max)
                    max = DP[j];
            }
            DP[i] = max+1;
            if(DP[i]>maxDP)
                maxDP = DP[i];
        }

        return maxDP;
        */

        List<Integer> sol = new ArrayList<>();
        sol.add(nums[0]);
        for(int i=1;i<nums.length;i++) {
            if(nums[i] > sol.get(sol.size()-1)) {
                //extend the sequence
                sol.add(nums[i]);
            } else {
                //replace the lowest element nums[i] is greater than
                int index = search(sol,nums[i]);
                sol.set(index,nums[i]);
            }
        }

        return sol.size();
    }

    private int search(List<Integer> list, int target) {
        int left = 0;
        int right = list.size()-1;
        while(left <= right) {
            int middle = (left+right)/2;
            if(middle==0 && list.get(middle) >= target)
                return 0;

            if(list.get(middle) >= target && middle > 0 && list.get(middle-1)<target) {
                return middle;
            } else if(target > list.get(middle)) {
                left = middle+1;
            } else {
                right = middle-1;
            }
        }
        return -1;
    }
}
