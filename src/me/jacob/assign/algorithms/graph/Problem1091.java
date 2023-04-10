package me.jacob.assign.algorithms.graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem1091 {

    /**
     * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
     *
     * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
     *
     * All the visited cells of the path are 0.
     * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
     * The length of a clear path is the number of visited cells of this path.
     */

    public int shortestPathBinaryMatrix(int[][] grid) {
        //use BFS
        if(grid[0][0]==1)
            return -1;

        Queue<Integer> BFS = new ArrayDeque<>();

        BFS.add(0);
        grid[0][0] = -1;

        while(!BFS.isEmpty()) {
            int node = BFS.poll();
            int row = getRow(node,grid.length);
            int col = getCol(node,grid.length);

            if(row==grid.length-1 && col==grid.length-1)
                return -grid[row][col];

            //add neighbours
            for(int i=-1;i<=1;i++) {
                if(i==-1 && row == 0)
                    continue;

                if(i==1 && row==grid.length-1)
                    continue;

                for(int j=-1;j<=1;j++) {
                    if(j==-1 && col==0)
                        continue;

                    if(j==1 && col == grid.length-1)
                        continue;

                    int newNode = (row+i)*grid.length + col + j;
                    if(grid[row+i][col+j]==0) {
                        BFS.add(newNode);
                        grid[row+i][col+j] = grid[row][col]-1;
                    }
                }
            }
        }
        return -1;
    }

    private int getRow(int node, int n) {
        return node/n;
    }

    private int getCol(int node, int n) {
        return node % n;
    }
}
