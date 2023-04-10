package me.jacob.assign.algorithms.array;

import java.util.Arrays;

public class Problem435 {

    /**
     * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of
     * intervals you need to remove to make the rest of the intervals non-overlapping.
     */

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a, b) -> a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]);
        int overlaps = 0;
        int collisions = 1;
        //loop through intervals to find overlaps
        int prev = 0;
        int i = 1;
        while(i<intervals.length) {
            if(intervals[prev][0] < intervals[i][1] && intervals[i][0] < intervals[prev][1]) {
                overlaps++;
                collisions++;
            } else {
                prev += collisions;
                collisions = 1;
            }
            i++;
        }
        return overlaps;
    }
}
