package me.jacob.assign.algorithms.array;

import java.util.Arrays;

public class Problem123 {

    public static void main(String[] args) {
        Problem123 p123 = new Problem123();
        int res = p123.maxProfit(new int[]{1, 5, 1, 7, 0, 2});
        System.out.println(res);
    }

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     */
    public int maxProfit(int[] prices) {
        //Optimal price for trading on day i, with transaction k
        int[][] DP = new int[prices.length][3];
        for (int k = 1; k >= 0; k--) {
            int prevMax = -prices[0];
            for(int i = 1;i<prices.length;i++) {
                //find out the optimal "low", When we trade the it is prices[i] - prices[j] + DP[j][j+1, sell time - buy time + best from previous transaction when we last bought
                //to avoid looping through and calculating this again, keep track of the optimal -prices[j] + DP[j][j+1], so that we can sell using this optimal value
                 prevMax = Math.max(prevMax,-prices[i] + DP[i][k+1]);

                 //compare the two strategies
                //if we don't trade then it is the price of the previous day, with the same transaction
                int noTrade = DP[i-1][k];

                //otherwise if we trade, then calculate the profit, which is the aforementioned prevMax variable we are tracking.
                int trade = prices[i] + prevMax;

                //set the optimal value for this many
                DP[i][k] = Math.max(trade,noTrade);
            }
        }

        return DP[prices.length-1][0];
    }

    /**
     * Solution 1 - Using backtracking
     */
    public int backtrack(int[] prices, int curr, int lastBuy, int transactions, int[][] DP, boolean[][] calculated) {
        if (curr >= prices.length)
            return 0;

        if (transactions >= 2)
            return 0;

        //if we are buying then retrieve from the DP array to solve overlapping sub-problems
        if (calculated[curr][transactions] && lastBuy == -1)
            return DP[curr][transactions];

        //we can either buy or sell
        if (lastBuy == -1) {
            //case 1, we buy
            int buyProfit = backtrack(prices, curr + 1, curr, transactions, DP, calculated);
            int noBuyProfit = backtrack(prices, curr + 1, -1, transactions, DP, calculated);

            //get the best
            DP[curr][transactions] = Math.max(buyProfit, noBuyProfit);
            calculated[curr][transactions] = true;
            return DP[curr][transactions];
        } else {
            //sell
            int sellProfit = prices[curr] - prices[lastBuy];
            sellProfit += backtrack(prices, curr + 1, -1, transactions + 1, DP, calculated);
            int noSellProfit = backtrack(prices, curr + 1, lastBuy, transactions, DP, calculated);
            return Math.max(sellProfit, noSellProfit);
        }
    }

    /**
     * Solution 2 - Using DP - Attempt 1
     */
    public int maxProfit1(int[] prices) {
        //buying at a certain time, with x transactions.
        int[][] DP = new int[prices.length][3];
        for (int k = 1; k >= 0; k--) {
            for(int i = 1;i<prices.length;i++) {
                //first strategy -  don't trade -> profit from last day
                int noTrade = DP[i-1][k];
                int maxTrade = 0;
                for(int j = 0;j<i;j++) {
                    maxTrade = Math.max(maxTrade,prices[i] - prices[j] + DP[j][k+1]);
                }
                DP[i][k] = Math.max(maxTrade,noTrade);
            }
        }

        return DP[prices.length-1][0];
    }
}
