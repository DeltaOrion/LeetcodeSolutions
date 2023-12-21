package me.jacob.assign.algorithms.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem2948 {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int[][] reverseMapping = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            reverseMapping[i] = new int[]{nums[i], i};
        }

        Arrays.sort(reverseMapping, Comparator.comparingInt(o -> o[0]));
        int[] result = new int[nums.length];
        List<Integer> groupIndex = new ArrayList<>();
        List<Integer> groupValue = new ArrayList<>();
        groupIndex.add(reverseMapping[0][1]);
        groupValue.add(reverseMapping[0][0]);
        for (int i = 1; i < reverseMapping.length; i++) {
            if (reverseMapping[i][0] - groupValue.get(groupValue.size() - 1) <= limit) {
                groupIndex.add(reverseMapping[i][1]);
                groupValue.add(reverseMapping[i][0]);
            } else {
                groupIndex.sort(Integer::compareTo);
                for (int j = 0; j < groupIndex.size(); j++) {
                    result[groupIndex.get(j)] = groupValue.get(j);
                }

                groupIndex.clear();
                groupValue.clear();
            }
        }

        return result;
    }
}
