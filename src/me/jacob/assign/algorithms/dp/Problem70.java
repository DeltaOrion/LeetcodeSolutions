package me.jacob.assign.algorithms.dp;

public class Problem70 {

    /**
     * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
     *
     * You have the following three operations permitted on a word:
     *
     * Insert a character
     * Delete a character
     * Replace a character
     */
    public int minDistance(String word1, String word2) {

        int[][] DP = new int[word1.length()+1][word2.length()+1];
        //set distances from empty strings
        for(int i=0;i<DP[0].length;i++) {
            DP[0][i] = i;
        }

        for(int i=0;i<DP.length;i++) {
            DP[i][0] = i;
        }

        for(int i=0;i<word1.length();i++) {
            for(int j=0;j<word2.length();j++) {

                if(word1.charAt(i) == word2.charAt(j)) {
                    DP[i+1][j+1] = DP[i][j];
                } else {
                    int replace = DP[i][j];
                    int delete = DP[i][j + 1];
                    int insert = DP[i + 1][j];

                    DP[i+1][j+1] = Math.min(replace,Math.min(delete,insert))+1;
                }
            }
        }

        return DP[word1.length()][word2.length()];
    }
}
