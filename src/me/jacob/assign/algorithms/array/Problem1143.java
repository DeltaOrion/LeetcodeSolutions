package me.jacob.assign.algorithms.array;

public class Problem1143 {

    /**
     * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0
     * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
     *
     * For example, "ace" is a subsequence of "abcde".
     * A common subsequence of two strings is a subsequence that is common to both strings.
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] DP = new int[text1.length()+1][text2.length()+1];
        for(int i=0;i<=text1.length();i++) {
            DP[i][0] = 0;
        }

        for(int i=0;i<=text2.length();i++) {
            DP[0][i] = 0;
        }

        for(int i=0;i<text1.length();i++) {
            for(int j=0;j<text2.length();j++) {
                if(text1.charAt(i)==text2.charAt(j)) {
                    //we found a match update DP
                    DP[i+1][j+1] = DP[i][j] + 1;
                } else {
                    //set it to the max
                    DP[i+1][j+1] = Math.max(DP[i][j+1],DP[i+1][j]);
                }
            }
        }
        return DP[text1.length()][text2.length()];
    }
}
