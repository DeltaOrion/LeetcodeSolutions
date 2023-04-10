package me.jacob.assign.algorithms.array;

import java.util.*;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of
 * the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end]
 * that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not
 * have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 */
public class Problem57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> solution = new ArrayList<>();
        boolean inserted = false;
        for(int[] interval : intervals) {
            //check they are colliding
            if(!inserted && newInterval[1] < interval[0]) {
                solution.add(newInterval);
                inserted = true;
            }

            if(newInterval[0] <= interval[1] && newInterval[1] >= interval[0]) {
                newInterval = new int[]{
                        Math.min(newInterval[0],interval[0]),
                        Math.max(newInterval[1],interval[1])
                };
            } else {
                solution.add(interval);
            }
        }

        if(!inserted) {
            solution.add(newInterval);
        }

        return solution.toArray(new int[solution.size()][2]);
    }

    private int binarySearch(List<int[]> arr, int[] newInterval) {
        int left = 0;
        int right = arr.size() - 1;

        while(left <= right) {
            int middle = (left + right) / 2;
            if(arr.get(middle)[0] == newInterval[0]) {
                return middle;
            } else if(arr.get(middle)[0] < newInterval[0]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return left;
    }
}
