package me.jacob.assign.algorithms.dp;

import java.util.Arrays;

public class Problem1406 {

    public static void main(String[] args) {
        Problem1406 p = new Problem1406();
        var val = p.stoneGameIII(new int[]{-1, -2, -3});
        System.out.println(val);
    }

    public String stoneGameIII(int[] stoneValue) {
        int[] DP = new int[stoneValue.length];

        int suffixSum = stoneValue[stoneValue.length - 1];
        DP[stoneValue.length - 1] = stoneValue[stoneValue.length - 1];


        for (int i = stoneValue.length - 2; i >= 0; i--) {
            DP[i] = Integer.MIN_VALUE;
            suffixSum = suffixSum + stoneValue[i];
            for (int j = 1; j <= 3; j++) {
                if (i + j > stoneValue.length) {
                    break;
                }

                int opponentScore = i + j == stoneValue.length ? 0 : DP[i + j];
                int ourScore = suffixSum - opponentScore;
                DP[i] = Math.max(DP[i], ourScore);
            }
        }

        int aliceScore = DP[0];
        int bobScore = suffixSum - aliceScore;
        if (aliceScore > bobScore) {
            return "Alice";
        } else if (bobScore > aliceScore) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
