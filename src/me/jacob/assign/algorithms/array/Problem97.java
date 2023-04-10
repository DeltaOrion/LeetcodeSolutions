package me.jacob.assign.algorithms.array;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where s and t are divided into n and m
 * substrings
 *  respectively, such that:
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 */
public class Problem97 {

    public static void main(String[] args) {
        Problem97 problem97 = new Problem97();
        System.out.println(problem97.isInterleave("aabcc","dbbca","aadbbcbcac"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {

        if(s1.length() + s2.length() != s3.length())
            return false;

        /*
        boolean[][] invalid = new boolean[s1.length()+1][s2.length()+1];
        return isInterleave(s1,s2,s3,0,0,0,invalid);
        */
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public boolean isInterleave(String s1, String s2, String s3, int i, int j, int x, boolean[][] invalid) {
        if(invalid[i][j])
            return false;

        if(x==s3.length()) {
            return i == s1.length() && j == s2.length();
        }

        char target = s3.charAt(x);
        if(i < s1.length() && s1.charAt(i) == target) {
            if(isInterleave(s1,s2,s3,i+1,j,x+1,invalid))
                return true;
        }

        if(j < s2.length() && s2.charAt(j) == target) {
            if(isInterleave(s1,s2,s3,i,j+1,x+1,invalid))
                return true;
        }

        invalid[i][j] = true;
        return false;
    }
}
