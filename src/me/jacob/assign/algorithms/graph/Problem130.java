package me.jacob.assign.algorithms.graph;

public class Problem130 {

    /**
     * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
     *
     * A region is captured by flipping all 'O's into 'X's in that surrounded region.
     */

    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                if(!visited[i][j] && board[i][j]=='O' && isEncircled(board,visited,i,j))
                    floodFill(board,i,j,'X');
            }
        }
    }

    private boolean isEncircled(char[][] board, boolean[][] visited, int i, int j) {
        if(i<0 || i>=board.length||j<0 || j>=board[0].length) //we are at a border, return false
            return false;

        if(board[i][j] == 'X' || visited[i][j])
            return true;

        visited[i][j] = true;

        boolean A = isEncircled(board,visited,i+1,j);
        boolean B = isEncircled(board,visited,i-1,j);
        boolean C = isEncircled(board,visited,i,j+1);
        boolean D = isEncircled(board,visited,i,j-1);

        return A && B && C && D;
    }

    private void floodFill(char[][] board, int i, int j, char c) {
        if(i<0 || i>=board.length ||j<0 || j>=board[0].length) //we are at a border, return false
            return;

        if(board[i][j]==c)
            return;

        board[i][j] = c;

        floodFill(board,i+1,j,c);
        floodFill(board,i-1,j,c);
        floodFill(board,i,j+1,c);
        floodFill(board,i,j-1,c);

    }
}
