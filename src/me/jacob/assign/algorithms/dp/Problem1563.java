package me.jacob.assign.algorithms.dp;

public class Problem1563 {

    public static void main(String[] args) {
        Problem1563 p = new Problem1563();
        System.out.println(p.stoneGameV(new int[]{1,1,2}));
    }

    public int stoneGameV(int[] stoneValue) {
        int[] prefixSum = new int[stoneValue.length + 1];
        for (int i = 1; i <= stoneValue.length; i++) {
            prefixSum[i] = stoneValue[i - 1] + prefixSum[i - 1];
        }

        int[][] DP = new int[stoneValue.length][stoneValue.length];
        for (int i = 0; i < stoneValue.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                int sumLeft;
                int sumRight;
                if (i - j == 1) {
                    sumLeft = stoneValue[i];
                    sumRight = stoneValue[j];
                    DP[i][j] = Math.min(sumLeft, sumRight);
                } else {
                    for(int middle = j + 1;middle<=i;middle++) {
                        sumLeft = prefixSum[middle] - prefixSum[j];
                        sumRight = prefixSum[i + 1] - prefixSum[middle];

                        int val = 0;
                        if (sumLeft < sumRight) {
                            val = sumLeft + DP[middle - 1][j];
                        } else if (sumRight < sumLeft) {
                            val = sumRight + DP[i][middle];
                        } else {
                            val = sumLeft + Math.max(DP[middle - 1][j], DP[i][middle]);
                        }

                        DP[i][j] = Math.max(val, DP[i][j]);
                    }
                }
            }
        }

        return DP[DP.length - 1][0];
    }
}
