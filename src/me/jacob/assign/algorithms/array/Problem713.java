package me.jacob.assign.algorithms.array;

public class Problem713 {

    /**
     * Given an array of integers nums and an integer k, return the number of contiguous
     * subarrays where the product of all the elements in the subarray is strictly less than k.
     */

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //base case
        if(k==0)
            return 0;

        int currentProd = 1;
        int prev = 0;
        int addition = 0;
        int result = 0;

        for(int i=0;i<nums.length;i++) {
            currentProd *= nums[i];
            addition++;

            while(currentProd >= k && prev < i) {
                currentProd /= nums[prev];
                prev++;
                addition--;
            }

            if(currentProd < k) {
                result += addition;
            }
        }

        return result;
    }
}
