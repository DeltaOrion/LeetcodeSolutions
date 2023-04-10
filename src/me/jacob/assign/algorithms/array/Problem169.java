package me.jacob.assign.algorithms.array;

public class Problem169 {

    /**
     * Given an array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     */

    public int majorityElement(int[] nums) {
        int i = 1;
        int m = nums[0];
        for(int j=1;j<nums.length;j++) {
            if(m==nums[j]) {
                i++;
            } else {
                i--;
            }

            if(i==0) {
                m = nums[j];
                i = 1;
            }
        }

        return m;
    }
}
