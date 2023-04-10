package me.jacob.assign.algorithms.array;

import java.util.HashMap;
import java.util.Map;

public class Problem79 {

    /**
     * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
     */

    public boolean exist(char[][] board, String word) {
        //use pruning - this is a technique which removes obvious mismatches
        //before commencing a search

        if(board.length == 0 || board.length * board[0].length < word.length())
            return false;

        if(checkChars(board,word))
            return false;

        int prefix = getPrefix(word);
        int suffix = getSuffix(word);

        if(prefix > suffix) {
            word = new StringBuilder(word).reverse().toString();
        }

        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[i].length;j++) {
                if(findWord(board,word,0,i,j, new boolean[board.length][board[0].length]))
                    return true;
            }
        }
        return false;
    }

    private int getPrefix(String word) {
        if(word.length()==0)
            return 0;

        char preChar = word.charAt(0);
        for(int i=1;i<word.length();i++) {
            if(word.charAt(i)!=preChar)
                return i-1;
        }

        return word.length()-1;
    }

    private int getSuffix(String word) {
        if(word.length()==0)
            return 0;

        char preChar = word.charAt(word.length()-1);
        for(int i=word.length()-2;i>=0;i--) {
            if(word.charAt(i)!=preChar)
                return word.length() - (i+1);
        }

        return word.length()-1;
    }

    private boolean checkChars(char[][] board, String word) {
        Map<Character,Integer> chars = new HashMap<>();
        for(int i=0;i<word.length();i++) {
            chars.merge(word.charAt(i), 1, Integer::sum);
        }

        for (char[] value : board) {
            for (char c : value) {
                Integer res = chars.get(c);
                if (res != null) {
                    if (res == 1) {
                        chars.remove(c);
                    } else {
                        chars.put(c, res - 1);
                    }
                }
            }
        }
        return chars.size() != 0;
    }

    private boolean findWord(char[][] board, String word, int curr, int i, int j, boolean[][] visited) {
        if(curr == word.length()) {
            return true;
        }

        if(i>=board.length || i<0 || j>=board[i].length || j<0)
            return false;

        if(visited[i][j])
            return false;

        visited[i][j] = true;

        if(board[i][j] == word.charAt(curr)) {
            if(findWord(board,word,curr+1,i+1,j,visited))
                return true;

            if(findWord(board,word,curr+1,i-1,j,visited))
                return true;

            if(findWord(board,word,curr+1,i,j+1,visited))
                return true;

            if(findWord(board,word,curr+1,i,j-1,visited))
                return true;
        }

        visited[i][j] = false;

        return false;
    }
}
