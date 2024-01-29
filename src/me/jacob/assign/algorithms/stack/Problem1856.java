package me.jacob.assign.algorithms.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Problem1856 {

    public static void main(String[] args) {
        Problem1856 p = new Problem1856();
        p.maxSumMinProduct(new int[]{3, 1, 5, 6, 4, 2});
    }

    private final static int MOD = 1000000007;

    public int maxSumMinProduct(int[] n) {
        //calculate the prefix sum. This is useful for us to figure out what the sum of values is
        //between any two arbitrary positions in the array.
        long[] prefixSum = new long[n.length + 1];
        for (int i = 1; i <= n.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + n[i - 1];
        }
        long result = 0;

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n.length; i++) {
            //check if we have hit a new local minima. Let's call the position of this new local minima i. Keep popping any value
            //away that is higher than i. This is important as it allows us to save a lot of calculations
            //as in the stack we know all of the elements between i and the newly popped out j are at least greater than j. Thus the popped element
            //j MUST be the lowest thus far. As for whether this new local minima i fits in the calculation, we'll check that out later.
            while (!stack.isEmpty() && (n[i] < n[stack.peek()])) {
                int j = stack.pop(); //As described earlier we know j is the minimum value so far (anything greater would have been popped out when this j was i)
                int k; //k is the value just after the next minima (the one lower than j) which we'll call j1. We don't want to include j1 because j1 < j. The reason we want k
                //is because we always want to include values higher than j. For example lets say we had the array [2,6,5,4,6,1]. Now let's say i = 1, the newly popped j = 4, and j1 = 2. We
                //want to find the sum of the subarray where j = 4, which is [6,5,4,6], we need to include the 6 and the 5 which due to the way this stack works is ALWAYS above the 2.
                if (stack.isEmpty()) {
                    k = 0; //In the case where the stack is empty we are at the smallest value in the array, including bigger values is always good (because it increases the sum), so we would be insane
                    //not to include the whole array.
                } else {
                    k = stack.peek() + 1;
                }

                //store the result of the calculation if it is a better option than what we've got
                result = Math.max(result, (prefixSum[i] - prefixSum[k]) * n[j]);
            }

            stack.push(i);
        }

        //now we have to do this process one last time at the end of the array. There are two cases
        // 1. The end of the array was in ascending order so a minima never came in e.g. [1,1,1,1,5,6,7]
        // 2. The last minima needs to be included e.g. [6,5,4]
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k;
            if (stack.isEmpty()) {
                k = 0;
            } else {
                k = stack.peek() + 1;
            }

            result = Math.max(result, (prefixSum[n.length] - prefixSum[k]) * n[j]);
        }

        return (int) (result % MOD);
    }
}
