package me.jacob.assign.algorithms.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem347 {

    /**
     * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
     */

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> frequency = new HashMap<>();
        //counting sort - range = o-n
        //worse case O(n+n) = O(2n) = O(n)

        int[] count = new int[nums.length+1];

        for (int n : nums) {
            frequency.put(n, frequency.getOrDefault(n, 0) + 1);
        }

        for(Map.Entry<Integer,Integer> entry : frequency.entrySet()) {
            count[entry.getValue()]++;
        }

        for(int i=1;i<count.length;i++) {
            count[i] = count[i] + count[i-1];
        }

        int[] result = new int[frequency.size()];
        //loop through original element map
        //0 = top element, 1 = next top
        for(Map.Entry<Integer,Integer> entry : frequency.entrySet()) {
            result[result.length - count[entry.getValue()]] = entry.getKey();
            count[entry.getValue()]--;
        }

        return Arrays.copyOfRange(result,0,k);

    }
}
