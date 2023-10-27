package me.jacob.assign.algorithms.dp;

public class Problem5 {

    public String longestPalindromeDP(String s) {
        int[][] DP = new int[s.length()][s.length()];
        int maxVal = 0;
        int maxI = 0;
        int maxJ = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j >= 0; j--) {
                //if we are at the same letter, we know all 1 length long strings are palendromic.
                if (i == j) {
                    DP[i][j] = 1;
                } else if (s.charAt(i) == s.charAt(j)) {
                    if (i - j == 1) {
                        DP[i][j] = 2;
                    } else if (DP[i - 1][j + 1] > 0) {
                        DP[i][j] = DP[i - 1][j + 1] + 2;
                    }
                }

                if (DP[i][j] > maxVal) {
                    maxVal = DP[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }

        return s.substring(maxJ, maxI + 1);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int maxI = 0;
        int maxJ = 1;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > maxJ - maxI) {
                maxI = i - (len - 1) / 2;
                maxJ = i + len / 2;
            }
        }

        return s.substring(maxI, maxJ);
    }

    public int expandAroundCenter(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        return j - i - 1;
    }
}
