package me.jacob.assign.algorithms.array;

public class Problem75 {

    /**
     *  Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
     *
     * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
     *
     * You must solve this problem without using the library's sort function.
     */

    public void sortColors(int[] nums) {
        int i0 = -1;
        int i1 = -1;

        for(int j=0;j<nums.length;j++) {
            if(nums[j]==0) {
                i0++;
                i1++;
                swap(nums,j,i0);
                if(i1!=i0)
                    swap(nums,j,i1);

            } else if(nums[j]==1) {
                i1++;
                swap(nums,j,i1);
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
