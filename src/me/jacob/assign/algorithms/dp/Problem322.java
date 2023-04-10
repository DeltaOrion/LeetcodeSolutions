package me.jacob.assign.algorithms.dp;

public class Problem322 {

    /**
     * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
     *
     * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * You may assume that you have an infinite number of each kind of coin.
     */

    public int coinChange(int[] coins, int amount) {
        int[] DP = new int[amount+1];
        DP[0] = 0;
        for(int i=1;i<=amount;i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i - coin >= 0) {
                    if(DP[i-coin]!=-1) {
                        min = Math.min(min, DP[i - coin]);
                    }
                }
                DP[i] = min == Integer.MAX_VALUE ? -1 : min+1;
            }
        }
        return DP[amount];
    }
}
