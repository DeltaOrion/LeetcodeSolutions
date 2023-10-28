package me.jacob.assign.algorithms.dp;

import java.util.Arrays;

public class Problem1140 {

    private final static int BOB = 0;
    private final static int ALICE = 1;

    public static void main(String[] args) {
        Problem1140 p = new Problem1140();
        int val = p.stoneGameII(new int[]{2, 7, 9, 4, 4});
        System.out.println(val);
    }

    public int stoneGameII(int[] piles) {
        int[] suffixSum = new int[piles.length + 1];
        suffixSum[piles.length - 1] = piles[piles.length - 1];
        for (int i = piles.length - 2; i >= 0; i--) {
            suffixSum[i] = piles[i] + suffixSum[i + 1];
        }

        int[][] DP = new int[piles.length][piles.length + 1];
        for (int i = piles.length - 1; i >= 0; i--) {
            for (int m = 1; m <= piles.length; m++) {
                //base case
                if (i + 2 * m >= piles.length) {
                    DP[i][m] = suffixSum[i];
                } else {
                    for (int x = 1; x <= 2 * m; x++) {
                        int opponentScore = DP[i + x][Math.max(m, x)];
                        int ourScore = suffixSum[i] - opponentScore;
                        DP[i][m] = Math.max(DP[i][m], ourScore);
                    }
                }
            }
        }

        return DP[0][1];
    }

    public int stoneGameIIRecurseMemo(int[] piles) {
        int[][][] DP = new int[2][piles.length][piles.length];
        for (int[][] playerDP : DP) {
            for (int[] MDP : playerDP) {
                Arrays.fill(MDP, -1);
            }
        }

        int[] prefixSum = new int[piles.length];
        prefixSum[0] = piles[0];
        for (int i = 1; i < piles.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + piles[i];
        }

        return solve(DP, piles, prefixSum, 1, 0, ALICE);
    }

    private int solve(int[][][] DP, int[] piles, int[] prefixSum, int M, int i, int player) {
        if (i > piles.length) {
            return 0;
        }

        if (i + 2 * M >= piles.length) {
            if (player == BOB) {
                return 0;
            }

            return prefixSum[piles.length - 1] - prefixSum[i - 1];
        }

        if (DP[player][M][i] != -1) {
            return DP[player][M][i];
        }

        int max = player == ALICE ? 0 : Integer.MAX_VALUE;
        int total = 0;

        for (int x = 0; x < 2 * M; x++) {
            if (player == ALICE) {
                //we want to maximize our score
                total += piles[i + x];
                int after = solve(DP, piles, prefixSum, Math.max(x + 1, M), i + x + 1, BOB);
                if (total + after > max) {
                    max = total + after;
                }
            } else {
                //we want to minimize alice score
                max = Math.min(max, solve(DP, piles, prefixSum, Math.max(x + 1, M), i + x + 1, ALICE));
            }
        }

        DP[player][M][i] = max;
        return DP[player][M][i];
    }

}
