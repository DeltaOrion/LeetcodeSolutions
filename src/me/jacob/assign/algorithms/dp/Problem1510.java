package me.jacob.assign.algorithms.dp;

public class Problem1510 {

    public static void main(String[] args) {
        Problem1510 p = new Problem1510();
        System.out.println(p.winnerSquareGame(4));
    }

    public boolean winnerSquareGame(int n) {
        boolean[] DP = new boolean[n + 1];
        int sqrt = (int) Math.ceil(Math.sqrt(n));
        for (int i = 2; i <= sqrt; i++) {

            for (int x = 1; i + x * x <= n; x++) {
                DP[i + x * x] = true;
            }
        }

        return DP[n];
    }

    /*
    public boolean winnerSquareGame(int n) {
        boolean[] DP = new boolean[n + 1];
        DP[1] = true;

        for (int i = 2; i <= n; i++) {
            int x = 1;
            while (x * x <= i) {
                if (!DP[i - x * x]) {
                    DP[i] = true;
                }

                x++;
            }
        }

        return DP[n];
    }
     */
}
