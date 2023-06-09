package me.jacob.assign.algorithms.array;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem122 {

    public static void main(String[] args) {
        Problem122 p122 = new Problem122();
        int res = p122.maxProfit(new int[]{1,5,1,7,0,2});
        System.out.println(res);
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        int i = 0;
        int j = 1;

        while (j < prices.length) {
            if(prices[j] < prices[j-1]) {
                //profit
                profit += prices[j-1] - prices[i];
                i = j;
            }
            j++;
        }

        //edge case where the last one spikes up
        if(prices[j-1] > prices[i])
            profit += prices[j-1] - prices[i];

        return profit;
    }
}
