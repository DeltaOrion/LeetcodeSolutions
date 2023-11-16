package me.jacob.assign.algorithms.array;

import java.util.*;

public class Problem239 {

    public static void main(String[] args) {
        Problem239 p = new Problem239();
        System.out.println(Arrays.toString(p.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] maxes = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }

            //remove the first
            deque.addLast(nums[i]);
            if (i - k + 1 >= 0) {
                maxes[i - k + 1] = deque.peekFirst();
                if (nums[i - k + 1] == deque.peek()) {
                    deque.poll();
                }
            }
        }

        return maxes;
    }
}
