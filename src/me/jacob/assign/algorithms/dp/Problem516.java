package me.jacob.assign.algorithms.dp;

import java.util.Arrays;

public class Problem516 {

    public static void main(String[] args) {
        Problem516 p = new Problem516();
        System.out.println(p.longestPalindromeSubseq("BABCBAB"));
    }

    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseqDP(s);
        //return longestPalindromeSubseqBacktrack(s,0,s.length()-1);
    }

    public int longestPalindromeSubseqDP(String s) {
        //DP is a table which shows the largest palindrome between a start and
        //end index where the y axis represents the starting index
        //and the x axis represents the ending index.

        int[][] DP = new int[s.length()][s.length()];
        for(int i=0;i<s.length();i++) {
            //palindrome of a single character is always size 1
            DP[i][i] = 1;
        }

        for(int end=0;end<s.length();end++) {
            for(int start = end-1;start>=0;start--) {
                //if the two characters are equal, then it is palindromic
                if(s.charAt(start) == s.charAt(end)) {
                    if(end - start == 1) {
                        DP[start][end] = 2;
                    } else {
                        //the largest palindrome between them + the 2 new characters
                        //contributing to the palindrome
                        DP[start][end] = DP[start + 1][end-1] + 2;
                    }
                } else {
                    //otherwise this new character contributes nothing to the palindrome
                    //use the largest instead
                    DP[start][end] = Math.max(
                            DP[start+1][end],
                            DP[start][end-1]
                    );
                }
            }
        }

        return DP[0][s.length()-1];
    }

    public int longestPalindromeSubseqBacktrack(String s, int start, int end) {
        //use a recursive top down approach
        if(start == end)
            return 1;

        if(start > end) {
            return 0;
        }

        if(s.charAt(start) == s.charAt(end)) {
            return 2 + longestPalindromeSubseqBacktrack(s,start + 1, end - 1);
        } else {
            return Math.max(
                    longestPalindromeSubseqBacktrack(s,start + 1,end),
                    longestPalindromeSubseqBacktrack(s, start,end - 1)
            );
        }
    }
}
