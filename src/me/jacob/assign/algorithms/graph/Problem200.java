package me.jacob.assign.algorithms.graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem200 {

    /**
     * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     *
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     */

    public int numIslands(char[][] grid) {
        int nodes = grid.length * grid[0].length;

        int n = grid[0].length;
        boolean[] visited = new boolean[nodes];
        Queue<Integer> BFS = new ArrayDeque<>();
        int islands = 0;

        for(int i=0;i<nodes;i++) {
            if(!visited[i] && grid[getRow(i,n)][getCol(i,n)]=='1') {
                BFS.add(i);
                while(!BFS.isEmpty()) {
                    int node = BFS.poll();
                    if(!visited[node]) {
                        visited[node] = true;
                        int row = getRow(node,n);
                        int col = getCol(node,n);

                        if(grid[row][col] == '1') {
                            //add neighbours
                            if(row>0) {
                                BFS.add(node-n);
                            }

                            if(row < grid.length-1) {
                                BFS.add(node+n);
                            }

                            if(col > 0) {
                                BFS.add(node-1);
                            }

                            if(col < n-1) {
                                BFS.add(node+1);
                            }
                        }
                    }
                }
                islands++;
            }
        }
        return islands;
    }

    private int getRow(int node, int n) {
        return node / n;
    }

    private int getCol(int node, int n) {
        return node % n;
    }
}
