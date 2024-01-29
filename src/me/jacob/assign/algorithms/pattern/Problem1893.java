package me.jacob.assign.algorithms.pattern;

import java.util.Arrays;
import java.util.Comparator;

public class Problem1893 {

    public static void main(String[] args) {
        Problem1893 p = new Problem1893();
        p.successfulPairs(new int[] {3,1,2}, new int[] {8,5,8}, 16);
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int[] result = new int[spells.length];
        for(int i = 0;i<spells.length;i++) {
            int spell = spells[i];
            int minimumMultiple = (int) Math.ceil((double) success / spell);
            int position = binarySearch(potions, minimumMultiple);
            if(position >= 0) {
                result[i] = potions.length - position;
            }
        }

        return result;
    }

    private int binarySearch(int[] arr, int minValue) {
        int left = 0;
        int right = arr.length - 1;
        while(left <= right) {
            //8, 5, 8
            int middle = (left + right) / 2;
            if(arr[middle] < minValue) {
                left = middle + 1;
            } else if(arr[middle] >= minValue && middle == 0) {
                return 0;
            } else if(arr[middle] >= minValue && arr[middle - 1] < minValue) {
                return middle;
            } else {
                right = middle - 1;
            }
        }

        return -1;
    }
}
