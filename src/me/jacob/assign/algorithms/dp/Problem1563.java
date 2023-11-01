package me.jacob.assign.algorithms.dp;

import java.util.Arrays;

public class Problem1563 {

    public static void main(String[] args) {
        Problem1563 p = new Problem1563();
        System.out.println(p.stoneGameV(new int[]{30903, 38111, 41840, 60598, 84513, 117898, 127733, 130461, 149604, 160886, 169614, 174907, 248345, 250181, 288673, 302598, 325134, 369558, 389473, 392977, 397273, 413269, 420694, 451901, 459194, 469824, 496075, 531944, 550481, 564955, 590044, 635671, 637560, 638431, 658993, 687473, 709734, 740089, 744007, 745858, 753062, 775115, 780092, 786877, 793796, 810733, 823645, 829132, 841211, 843017, 881397, 888325, 911530, 928316, 932055, 991263}));
    }

    public int stoneGameV(int[] stoneValue) {
        int[] prefixSum = new int[stoneValue.length + 1];
        int[][] opt = new int[stoneValue.length][stoneValue.length];
        for (int i = 1; i <= stoneValue.length; i++) {
            prefixSum[i] = stoneValue[i - 1] + prefixSum[i - 1];
            opt[i - 1][i - 1] = i - 1;
        }

        int[][] DP = new int[stoneValue.length][stoneValue.length];
        for (int j = 1; j < stoneValue.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                int sumLeft;
                int sumRight;
                if (j - i == 1) {
                    sumLeft = stoneValue[i];
                    sumRight = stoneValue[j];
                    opt[i][j] = i;
                    DP[i][j] = Math.min(sumLeft, sumRight);
                } else {
                    for (int k = Math.max(0, opt[i][j - 1] - 1); k <= Math.min(j - 1, opt[i + 1][j] + 1); k++) {
                        sumLeft = prefixSum[k + 1] - prefixSum[i];
                        sumRight = prefixSum[j + 1] - prefixSum[k + 1];

                        int val;
                        if (sumLeft < sumRight) {
                            val = sumLeft + DP[i][k];
                        } else if (sumRight < sumLeft) {
                            val = sumRight + DP[k + 1][j];
                        } else {
                            val = sumLeft + Math.max(DP[i][k], DP[k + 1][j]);
                        }

                        if (val > DP[i][j]) {
                            DP[i][j] = val;
                            opt[i][j] = k;
                        }
                    }
                }
            }
        }

        return DP[0][DP.length - 1];
    }
}
