package me.jacob.assign.algorithms.backtracking;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 */
public class Problem980 {

    private static int count = 0;

    public static void main(String[] args) {
        System.out.println(uniquePathsIII(new int[][]{{0,2,0,0,0},{0,0,0,1,0}}));
    }

    public static int uniquePathsIII(int[][] grid) {
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid.length;j++) {
                if(grid[i][j] == 1) {
                    backTrack(grid,i,j,new boolean[grid.length][grid[0].length]);
                }
            }
        }
        return count;
    }

    public static void backTrack(int[][] grid, int i, int j, boolean[][] covered) {
        //if we walked over the square or we are out of bounds
        //then terminate
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j] == -1 || covered[i][j])
            return;

        //if we have reached the finish, terminate
        if(grid[i][j] == 2) {
            if(check(grid,covered))
                count++;

            return;
        }

        covered[i][j] = true;
        backTrack(grid,i+1,j,covered);
        backTrack(grid,i,j+1,covered);
        backTrack(grid,i-1,j,covered);
        backTrack(grid,i,j-1,covered);
        covered[i][j] = false;
    }

    public static boolean check(int[][] grid, boolean[][] covered) {
        for(int x=0;x<grid.length;x++) {
            for(int y=0;y<grid[0].length;y++) {
                if(grid[x][y] == 0 && !covered[x][y])
                    return false;
            }
        }

        return true;
    }
}
