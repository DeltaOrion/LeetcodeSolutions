package me.jacob.assign.algorithms.array;

import java.util.Arrays;

public class Problem2434 {

    public static void main(String[] args) {
        Problem2434 p = new Problem2434();
        String[] nums = new String[]{"24", "37", "96", "04"};
        int[][] queries = new int[][]{{2, 1}, {2, 2}};
        System.out.println(Arrays.toString(p.smallestTrimmedNumbers(nums, queries)));
    }

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] solution = new int[queries.length];

        Node[] unsorted = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            unsorted[i] = new Node(nums[i], i);
        }

        int digit = nums[0].length() - 1;
        int len = nums[0].length();

        int finished = 0;
        while (digit >= 0 && finished < queries.length) {
            unsorted = countingSort(unsorted, digit);
            int qIndex = 0;
            for (int[] query : queries) {
                int trimi = query[1];
                int ki = query[0];
                if (len - trimi == digit) {
                    solution[qIndex] = unsorted[ki - 1].index;
                    finished++;
                }
                qIndex++;
            }
            digit--;
        }

        return solution;
    }

    public Node[] countingSort(Node[] unsorted, int digit) {
        int[] buckets = new int[10];
        for (Node num : unsorted) {
            buckets[num.value.charAt(digit) - '0']++;
        }

        for (int i = 1; i < buckets.length; i++) {
            buckets[i] = buckets[i] + buckets[i - 1];
        }

        Node[] sorted = new Node[unsorted.length];
        for (int i = unsorted.length - 1; i >= 0; i--) {
            sorted[--buckets[unsorted[i].value.charAt(digit) - '0']] = unsorted[i];
        }

        return sorted;
    }

    /*
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int[] solution = new int[queries.length];
        int qIndex = 0;
        for (int[] query : queries) {
            int ki = query[0];
            int trimi = query[1];
            PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
                String s1 = o1.value;
                String s2 = o2.value;

                for (int i = 0; i < s1.length(); i++) {
                    if (s1.charAt(i) > s2.charAt(i)) {
                        return -1;
                    } else if (s2.charAt(i) > s1.charAt(i)) {
                        return 1;
                    }
                }

                return Integer.compare(o2.index, o1.index);
            });

            int index = 0;
            for (String num : nums) {
                queue.add(new Node(num.substring(num.length() - trimi), index));
                if (queue.size() > ki) {
                    queue.poll();
                }

                index++;
            }

            solution[qIndex] = queue.poll().index;
            qIndex++;
        }

        return solution;
    }
     */
    private class Node {
        String value;
        int index;

        public Node(String value, int index) {
            this.index = index;
            this.value = value;
        }
    }
}
