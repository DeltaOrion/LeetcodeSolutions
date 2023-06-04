package me.jacob.assign.algorithms.array;

public class Problem36 {

    public static void main(String[] args) {
        char[][] boards = new char[][]{{'.','.','4','.','.','.','6','3','.'},{'.','.','.','.','.','.','.','.','.'},{'5','.','.','.','.','.','.','9','.'},{'.','.','.','5','6','.','.','.','.'},{'4','.','3','.','.','.','.','.','1'},{'.','.','.','7','.','.','.','.','.'},{'.','.','.','5','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.','.'}};
        Problem36 p = new Problem36();
        p.isValidSudoku(boards);
    }

    public boolean isValidSudoku(char[][] board) {
        boolean[][] cellsValid = new boolean[board.length][board.length];
        boolean[][] colsValid = new boolean[board.length][board.length];

        for(int i=0;i<board.length;i++) {
            boolean[] rowValid = new boolean[board.length];
            for(int j=0;j<board.length;j++) {
                if(board[i][j] == '.')
                    continue;

                int x = board[i][j] - '1';
                if(rowValid[x])
                    return false;

                rowValid[x] = true;
                if(colsValid[j][x])
                    return false;

                colsValid[j][x] = true;
                int cell = (i / 3) * 3 + j / 3;
                if(cellsValid[cell][x])
                    return false;

                cellsValid[cell][x] = true;
            }
        }

        return true;
    }
}
