package me.jacob.assign.algorithms.array;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
 *
 * Return the sorted array.
 */
public class Problem451 {
    public String frequencySort(String s) {
        Map<Character,Integer> freq = new HashMap<>();

        //count frequency
        for(int i=0;i<s.length();i++) {
            Integer res = freq.get(s.charAt(i));
            if(res==null) {
                freq.put(s.charAt(i),1);
            } else {
                freq.put(s.charAt(i),res+1);
            }
        }

        //sort frequency
        PriorityQueue<Map.Entry<Character,Integer>> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        for(Map.Entry<Character,Integer> entry : freq.entrySet()) {
            queue.add(entry);
        }

        StringBuilder result = new StringBuilder();
        while(!queue.isEmpty()) {
            Map.Entry<Character,Integer> entry = queue.poll();
            for(int i=0;i<entry.getValue();i++) {
                result.append(entry.getKey());
            }
        }

        return result.toString();

    }
}
