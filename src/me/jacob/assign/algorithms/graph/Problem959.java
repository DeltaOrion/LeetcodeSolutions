package me.jacob.assign.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class Problem959 {

    public static void main(String[] args) {
        String[] grid = new String[]{
                " /\\",
                " \\/",
                "\\  "
        };

        Problem959 p = new Problem959();
        System.out.println(p.regionsBySlashes(grid));
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[] unionFind = new int[(n - 1) * (n - 1) + 1];
        int root = (n - 1) * (n - 1);
        int result = 1;

        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }

        for (int i = 0; i < grid.length; i++) {
            String row = grid[i];
            for (int j = 0; j < row.length(); j++) {
                int c1;
                int c2;

                if (row.charAt(j) == '/') {
                    c1 = getCoord(i, j + 1, n, root);
                    c2 = getCoord(i + 1, j, n, root);
                } else if (row.charAt(j) == '\\') {
                    c1 = getCoord(i, j, n, root);
                    c2 = getCoord(i + 1, j + 1, n, root);
                } else {
                    continue;
                }

                if (cycle(unionFind, c1, c2)) {
                    result++;
                } else {
                    union(unionFind, c1, c2);
                }
            }
        }

        return result;
    }

    private int getCoord(int x, int y, int n, int root) {
        if (x == 0 || y == 0 || x == n || y == n) {
            return root;
        }

        return (x - 1) * (n-1) + (y - 1);
    }

    private int findSet(int[] unionFind, int i) {
        while (unionFind[i] != i) {
            i = unionFind[i];
        }

        return i;
    }

    private void union(int[] unionFind, int i, int j) {
        i = findSet(unionFind, i);
        j = findSet(unionFind, j);

        unionFind[i] = j;
    }

    private boolean cycle(int[] unionFind, int i, int j) {
        return findSet(unionFind, i) == findSet(unionFind, j);
    }
}
