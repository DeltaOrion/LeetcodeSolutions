package me.jacob.assign.learning;

public class KnuthOptimization {

    public static void main(String[] args) {
        KnuthOptimization opt = new KnuthOptimization();
        opt.minCost(new int[]{3,4,2,1,7}, 5);
    }

    public int minCost(int arr[], int N)
    {
        // Creating the prefix sum array
        int pref[] = new int[N + 1];
        int dp[][] = new int[N][N];
        int opt[][] = new int[N][N];
        pref[0] = 0;

        // Loop to calculate the prefix sum
        for (int i = 0; i < N; i++) {
            pref[i + 1] = pref[i] + arr[i];
            opt[i][i] = i;
        }

        // Iterating through all sub-arrays
        // of length 2 or greater
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {

                // Cost function = sum of
                // all elements in the sub-array
                int cost = pref[j + 1] - pref[i];
                int mn = Integer.MAX_VALUE;
                for (int k = opt[i][j - 1];
                     k <= Math.min(j - 1, opt[i + 1][j]);
                     k++) {
                    if (mn >= dp[i][k]
                            + dp[k + 1][j] + cost) {

                        // Updating opt table
                        opt[i][j] = k;

                        // Updating minimum cost
                        mn = dp[i][k]
                                + dp[k + 1][j] + cost;
                    }
                }

                // dp transition
                dp[i][j] = mn;
            }
        }
        return dp[0][N - 1];
    }
}
