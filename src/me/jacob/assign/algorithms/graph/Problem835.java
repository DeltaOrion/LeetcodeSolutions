package me.jacob.assign.algorithms.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem835 {

    public static void main(String[] args) {
        Problem835 p = new Problem835();
        int[][] img1 = new int[][]{{1, 1, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] img2 = new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(p.largestOverlap(img1, img2));
    }

    public int largestOverlap(int[][] img1, int[][] img2) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (int x = 0; x < img1.length; x++) {
            for (int y = 0; y < img1.length; y++) {
                if (img1[x][y] == 1) {
                    coordinates.add(new Coordinate(x, y));
                }
            }
        }

        System.out.println(coordinates);

        int maxOverlap = 0;
        for (int x = 0; x < img2.length; x++) {
            for (int y = 0; y < img2.length; y++) {
                maxOverlap = Math.max(maxOverlap, checkStructure(img2, x, y, coordinates));
            }
        }
        return maxOverlap;
    }

    private int checkStructure(int[][] img2, int x, int y, List<Coordinate> coordinates) {
        int overlap = 0;
        for (Coordinate coordinate : coordinates) {
            int u = x + coordinate.x;
            int v = y + coordinate.y;

            if (u < 0 || v < 0 || u >= img2.length || v >= img2.length) {
                continue;
            }

            if (img2[u][v] == 0) {
                continue;
            }

            overlap++;
        }

        return overlap;
    }

    private static class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
}
