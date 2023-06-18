package me.jacob.assign.algorithms.array;

public class Problem188 {

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     *
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     */
    public int maxProfit(int k, int[] prices) {
        //Optimal price for trading on day i, with transaction k
        int[][] DP = new int[prices.length][k+1];
        for (int t = k-1; t >= 0; t--) {
            int prevMax = -prices[0];
            for(int i = 1;i<prices.length;i++) {
                //find out the optimal "low", When we trade the it is prices[i] - prices[j] + DP[j][j+1, sell time - buy time + best from previous transaction when we last bought
                //to avoid looping through and calculating this again, keep track of the optimal -prices[j] + DP[j][j+1], so that we can sell using this optimal value
                prevMax = Math.max(prevMax,-prices[i] + DP[i][t+1]);

                //compare the two strategies
                //if we don't trade then it is the price of the previous day, with the same transaction
                int noTrade = DP[i-1][t];

                //otherwise if we trade, then calculate the profit, which is the aforementioned prevMax variable we are tracking.
                int trade = prices[i] + prevMax;

                //set the optimal value for this many
                DP[i][t] = Math.max(trade,noTrade);
            }
        }

        return DP[prices.length-1][0];
    }
}
