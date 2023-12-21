package me.jacob.assign.learning;

import java.util.Arrays;
import java.util.Stack;

public class TravellingSalesmanProblemPath {

    public static void main(String[] args) {
        System.out.println(TSP(new int[][]{{0, 10, 15, 20}, {5, 0, 9, 10}, {6, 13, 0, 12}, {8, 8, 9, 0}}));
    }

    private static int TSP(int[][] graph) {
        int n = graph.length;
        int[][] DP = new int[n][1 << (n)];
        int[][] paths = new int[n][1 << (n)];
        for (int[] row : DP) {
            Arrays.fill(row, -1); // Initialize DP array with -1
        }

        int min = Integer.MAX_VALUE;
        int kMin = 0;
        for (int i = 0; i < n; i++) {
            int res = helper(graph, DP, paths, i, n, (1 << n) - 1 - (1 << i));
            if (res < min) {
                min = res;
                kMin = i;
            }
        }

        Stack<Integer> path = new Stack<>();
        int k = kMin;
        int mask = ((1 << n) - 1 - (1 << kMin));
        while (mask != 0) {
            path.push(k);
            mask = mask & ~(1 << k);
            k = paths[k][mask];
        }

        System.out.println(path);

        return min;
    }

    private static int helper(int[][] graph, int[][] DP, int[][] path, int i, int n, int mask) {
        // Check if all nodes except the start node have been visited
        if (mask == 0) {
            return 0;
        }

        if (DP[i][mask] != -1) {
            return DP[i][mask];
        }

        int min = Integer.MAX_VALUE;
        int kMin = 0;
        for (int k = 0; k < n; k++) {
            // check that the node k is in the set
            if ((mask & (1 << k)) != 0) {
                int res = graph[i][k] + helper(graph, DP, path, k, n, mask & ~(1 << k));
                if (res < min) {
                    min = res;
                    kMin = k;
                }
            }
        }

        DP[i][mask] = min;
        path[i][mask] = kMin;
        return min;
    }
}
