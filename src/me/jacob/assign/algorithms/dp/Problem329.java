package me.jacob.assign.algorithms.dp;

public class Problem329 {

    public static void main(String[] args) {
        Problem329 p = new Problem329();
        int[][] grid = new int[][]{
                {1, 1},
                {3, 4},
                {7, 1}
        };

        System.out.println(p.longestIncreasingPath(grid));
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] DP = new int[m][n];

        //initialize DP array
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                DP[i][j] = -1;
            }
        }

        //DFS
        int length = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                length = Math.max(length,longestIncreasingPath(matrix, DP, i, j));
            }
        }

        return length;
    }

    private int longestIncreasingPath(int[][] matrix, int[][] DP, int i, int j) {
        //DFS and memorise to avoid repeated calculations.
        //if we have previously calculated the solution then use that result
        if (DP[i][j] != -1) {
            return DP[i][j];
        }

        int length = 0;
        //check if we have an increasing path, and if so count the path in the direction
        //UP
        if (i > 0 && matrix[i - 1][j] > matrix[i][j]) {
            length = Math.max(length, longestIncreasingPath(matrix, DP, i - 1, j));
        }
        //DOWN
        if (i < matrix.length - 1 && matrix[i + 1][j] > matrix[i][j]) {
            length = Math.max(length, longestIncreasingPath(matrix, DP, i + 1, j));
        }

        //LEFT
        if (j > 0 && matrix[i][j - 1] > matrix[i][j]) {
            length = Math.max(length, longestIncreasingPath(matrix, DP, i, j - 1));
        }

        //RIGHT
        if (j < matrix[i].length - 1 && matrix[i][j + 1] > matrix[i][j]) {
            length = Math.max(length, longestIncreasingPath(matrix, DP, i, j + 1));
        }

        return (DP[i][j] = (1 + length));
    }
}
