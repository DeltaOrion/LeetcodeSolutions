package me.jacob.assign.algorithms.array;

import java.util.ArrayList;
import java.util.List;

public class Problem986 {

    /**
     * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
     *
     * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
     */

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> solution = new ArrayList<>();
        int i =0;
        int j =0;

        while(i<firstList.length && j<secondList.length) {
            int low = Math.max(firstList[i][0],secondList[j][0]);
            int high = Math.min(firstList[i][1],secondList[j][1]);

            //they are colliding
            if(low<=high) {
                solution.add(new int[]{low,high});
            }

            if(firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return solution.toArray(new int[solution.size()][]);
    }
}
