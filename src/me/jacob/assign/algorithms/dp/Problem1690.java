package me.jacob.assign.algorithms.dp;

import java.util.Arrays;

public class Problem1690 {

    public static void main(String[] args) {
        Problem1690 p = new Problem1690();
        p.stoneGameVII(new int[]{5, 3, 1, 4, 2});
    }

    public int stoneGameVII(int[] stones) {
        int[][] DP = new int[stones.length][stones.length];
        int[] prefixSum = new int[stones.length + 1];
        for (int i = 1; i <= stones.length; i++) {
            prefixSum[i] = stones[i - 1] + prefixSum[i - 1];
        }

        for (int j = 1; j < stones.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                int sumLeft = prefixSum[j] - prefixSum[i];
                int sumRight = prefixSum[j + 1] - prefixSum[i + 1];

                int diffLeft = sumLeft - DP[i][j - 1];
                int diffRight = sumRight - DP[i + 1][j];

                DP[i][j] = Math.max(diffLeft, diffRight);
            }
        }

        for (int[] row : DP) {
            System.out.println(Arrays.toString(row));
        }
        return DP[0][stones.length - 1];
    }
}
