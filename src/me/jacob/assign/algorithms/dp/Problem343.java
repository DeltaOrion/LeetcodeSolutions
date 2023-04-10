package me.jacob.assign.algorithms.dp;

public class Problem343 {

    /**
     * Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.
     *
     * Return the maximum product you can get.
     */
    public int integerBreak(int n) {
        /*
        int[] DP = new int[n+1];
        DP[0] = 0;
        DP[1] = 0;
        for(int i=2;i<=n;i++) {
            int max = 0;
            for(int j=1;j<=(i/2);j++) {
                int result = i-j;
                int a = Math.max(result,DP[result]);
                int b = Math.max(j,DP[j]);
                max = Math.max(max,a*b);
            }
            DP[i] = max;
        }
        return DP[n];
        */

        if(n == 2)
            return 1;
        else if(n == 3)
            return 2;
        else if(n%3 == 0)
            return (int)Math.pow(3, n/3);
        else if(n%3 == 1)
            return 2 * 2 * (int) Math.pow(3, (n - 4) / 3);
        else
            return 2 * (int) Math.pow(3, n/3);
    }
}
