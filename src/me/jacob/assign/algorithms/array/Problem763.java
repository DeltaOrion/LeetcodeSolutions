package me.jacob.assign.algorithms.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem763 {

    /**
     * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
     *
     * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
     *
     * Return a list of integers representing the size of these parts.
     */

    public List<Integer> partitionLabels(String s) {
        Map<Character,Integer> iMap = new HashMap<>();
        //create intervals
        for(int i=0;i<s.length();i++) {
            iMap.put(s.charAt(i),i);
        }

        //merge intervals, we know they are sorted by starting value
        List<Integer> solution = new ArrayList<>();
        int anchor = 0;
        int j = 0;
        for(int i=0;i<s.length();i++) {
            j = Math.max(j,iMap.get(s.charAt(i)));
            if(i==j) {
                solution.add(j-anchor + 1);
                anchor = i+1;
            }
        }

        return solution;
    }
}
