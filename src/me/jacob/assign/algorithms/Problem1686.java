package me.jacob.assign.algorithms;

import java.util.Arrays;

public class Problem1686 {

    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        Node[] valueSum = new Node[aliceValues.length];
        for (int i = 0; i < aliceValues.length; i++) {
            valueSum[i] = new Node(aliceValues[i] + bobValues[i], i);
        }

        Arrays.sort(valueSum, (a, b) -> Integer.compare(b.val, a.val));
        int alice = 0;
        int bob = 0;
        for (int i = 0; i < aliceValues.length; i += 2) {
            alice += aliceValues[valueSum[i].index];
        }

        for (int i = 1; i < bobValues.length; i += 2) {
            bob += bobValues[valueSum[i].index];
        }

        return Integer.compare(alice, bob);
    }

    private static class Node {
        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }

        private int val;
        private int index;
    }
}
