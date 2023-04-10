package me.jacob.assign.algorithms.array;

public class Problem11 {

    /**
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     *
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     *
     * Return the maximum amount of water a container can store.
     *
     * Notice that you may not slant the container.
     */

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int maxArea = 0;
        while(i<j) {
            maxArea = Math.max(maxArea,
                    Math.min(height[i],height[j]) * (j-i));

            if(height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}
