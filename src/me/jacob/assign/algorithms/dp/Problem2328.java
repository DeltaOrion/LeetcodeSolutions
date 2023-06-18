package me.jacob.assign.algorithms.dp;

public class Problem2328 {

    private final static int MODULO = (int)(1e9+7);

    public static void main(String[] args) {
        Problem2328 p = new Problem2328();
        int[][] grid = new int[][]{
                {1, 1},
                {3, 4},
                {7, 1}
        };

        System.out.println(p.countPaths(grid));
    }

    public int countPaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] DP = new int[m][n];

        //initialize DP array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                DP[i][j] = -1;
            }
        }

        //DFS
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count = (count + countPaths(grid, DP, i, j)) % MODULO;
            }
        }

        int L1Paths = m * n;
        return (count + L1Paths) % MODULO;
    }

    private int countPaths(int[][] grid, int[][] DP, int i, int j) {
        //DFS and memorise to avoid repeated calculations.
        //if we have previously calculated the solution then use that result
        if (DP[i][j] != -1) {
            return DP[i][j];
        }

        int count = 0;
        //check if we have an increasing path, and if so count the path in the direction
        //UP
        if (i > 0 && grid[i - 1][j] > grid[i][j]) {
            count += (1 + countPaths(grid, DP, i - 1, j));
        }
        //DOWN
        if (i < grid.length - 1 && grid[i + 1][j] > grid[i][j]) {
            count += (1 + countPaths(grid, DP, i + 1, j));
        }

        //LEFT
        if (j > 0 && grid[i][j - 1] > grid[i][j]) {
            count += (1 + countPaths(grid, DP, i, j - 1));
        }

        //RIGHT
        if (j < grid[i].length - 1 && grid[i][j + 1] > grid[i][j]) {
            count += (1 + countPaths(grid, DP, i, j + 1));
        }

        return (DP[i][j] = count % MODULO);
    }

}
