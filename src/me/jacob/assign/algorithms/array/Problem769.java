package me.jacob.assign.algorithms.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Problem769 {

    public static void main(String[] args) {
        Problem769 p = new Problem769();
        int result = p.maxChunksToSorted(new int[]{1, 4, 0, 2, 3, 5});
        System.out.println(result);
    }

    public int maxChunksToSorted(int[] arr) {
        int[] max = new int[arr.length];
        max[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }

        int count = 0;
        for(int i=0;i<arr.length;i++) {
            if(max[i] == i) {
                count++;
            }
        }

        return count;
    }

    public int maxChunksToSorted2(int[] arr) {
        List<Integer> highest = new ArrayList<>();

        for (int val : arr) {
            if (highest.size() == 0) {
                highest.add(val);
            } else if (val >= highest.get(highest.size() - 1)) {
                highest.add(val);
            } else {
                int currentHighest = highest.get(highest.size() - 1);
                int i = highest.size() - 2;
                while (i >= 0 && val < highest.get(i)) {
                    highest.remove(i + 1);
                    i--;
                }

                highest.set(i + 1, currentHighest);
            }
        }

        return highest.size();
    }
}
