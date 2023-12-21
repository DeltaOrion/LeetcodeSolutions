package me.jacob.assign.algorithms.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1569 {

    public static void main(String[] args) {
        Problem1569 p = new Problem1569();
        System.out.println(p.numOfWays(new int[]{3, 4, 5, 1, 2}));
    }

    private int mod = 1000000007;

    public int numOfWays(int[] nums) {
        BSTNode root = new BSTNode(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            insert(root, nums[i]);
        }

        List<Integer> sizes = new ArrayList<>();
        countTrees(root, sizes);

        //calc nFact
        long nFact = 1;
        for (int i = 2; i <= nums.length; i++) {
            nFact = (nFact * (i % mod)) % mod;
        }

        //multiply the sizes together
        long sizeMul = 1;
        for (int size : sizes) {
            sizeMul = (sizeMul * (size % mod)) % mod;
        }

        //now calculate the final result
        long permutations = ((nFact % mod * modInverse(sizeMul, mod) % mod) % mod);
        permutations = (permutations - (1 % mod)) % mod;
        return (int) permutations;
    }

    private long modInverse(long a, long m) {
        //using extended euler algorithm
        long x = 1;
        long y = 0;
        long m0 = m;

        while (m > 0) {
            long r = a % m;
            long q = a / m;

            a = m;
            m = r;

            long t = x - q * y;
            x = y;
            y = t;
        }

        if (x < 0) {
            x = x + m0;
        }

        return x;
    }

    private int countTrees(BSTNode root, List<Integer> sizes) {
        if (root == null) {
            return 0;
        }

        int leftCount = countTrees(root.left, sizes);
        int rightCount = countTrees(root.right, sizes);
        int count = leftCount + rightCount + 1;
        sizes.add(count);
        return count;
    }

    private void insert(BSTNode root, int value) {
        while (root != null) {
            if (value > root.value) {
                if (root.left == null) {
                    root.left = new BSTNode(value);
                    root = null;
                } else {
                    root = root.left;
                }
            } else {
                if (root.right == null) {
                    root.right = new BSTNode(value);
                    root = null;
                } else {
                    root = root.right;
                }
            }
        }
    }

    private static class BSTNode {
        private int value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
