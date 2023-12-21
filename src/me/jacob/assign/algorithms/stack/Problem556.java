package me.jacob.assign.algorithms.stack;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem556 {

    public static void main(String[] args) {
        Problem556 p = new Problem556();
        p.nextGreaterElement(1999999999);
    }

    private final int[] MAX_VALUE = new int[]{2, 1, 4, 7, 4, 8, 3, 6, 4, 7};

    public int nextGreaterElement(int n) {
        //2147483647
        int[] digitArray = new int[10];
        int c = 9;
        while (n > 0) {
            digitArray[c] = n % 10;
            n = n / 10;
            c--;
        }

        c++;
        boolean swap = false;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = digitArray.length - 1; i > c; i--) {
            //if we can put an element higher we will
            queue.add(digitArray[i]);
            if (digitArray[i] > digitArray[i - 1]) {
                queue.add(digitArray[i - 1]);
                int j = i;
                int inflection = digitArray[i - 1];
                boolean inflectionFound = false;
                while (!queue.isEmpty()) {
                    int val = queue.poll();
                    if (!inflectionFound && val > inflection) {
                        digitArray[i - 1] = val;
                        inflectionFound = true;
                    } else {
                        digitArray[j] = val;
                        j++;
                    }
                }

                swap = true;
                break;
            }
        }

        if (!swap) {
            return -1;
        }

        //construct integer
        int val = 0;

        int comparator = 0;
        for (int i = 0; i < digitArray.length; i++) {
            if(comparator == 0) {
                if(digitArray[i] > MAX_VALUE[i]) {
                    return -1;
                } else if (digitArray[i] < MAX_VALUE[i]){
                    comparator = -1;
                }
            }
            val *= 10;
            val += digitArray[i];
        }

        return val;
    }
}
