package me.jacob.assign.algorithms.array;

public class Problem36 {

    public static void main(String[] args) {
        char[][] boards = new char[][]{{'.','.','4','.','.','.','6','3','.'},{'.','.','.','.','.','.','.','.','.'},{'5','.','.','.','.','.','.','9','.'},{'.','.','.','5','6','.','.','.','.'},{'4','.','3','.','.','.','.','.','1'},{'.','.','.','7','.','.','.','.','.'},{'.','.','.','5','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}};
        Problem36 p = new Problem36();
        p.isValidSudoku(boards);
    }

    /**
     * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
     *
     * Each row must contain the digits 1-9 without repetition.
     * Each column must contain the digits 1-9 without repetition.
     * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
     * Note:
     *
     * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
     * Only the filled cells need to be validated according to the mentioned rules.
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] cellsValid = new boolean[board.length][board.length];
        boolean[][] colsValid = new boolean[board.length][board.length];

        for(int i=0;i<board.length;i++) {
            boolean[] rowValid = new boolean[board.length];
            for(int j=0;j<board.length;j++) {
                if(board[i][j] == '.')
                    continue;

                //check row
                int x = board[i][j] - '1';
                if(rowValid[x])
                    return false;

                rowValid[x] = true;
                //check columns
                if(colsValid[j][x])
                    return false;

                colsValid[j][x] = true;
                //check 3x3 cells
                int cell = (i / 3) * 3 + j / 3;
                if(cellsValid[cell][x])
                    return false;

                cellsValid[cell][x] = true;
            }
        }

        return true;
    }
}
