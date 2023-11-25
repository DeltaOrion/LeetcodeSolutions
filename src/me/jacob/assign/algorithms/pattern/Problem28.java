package me.jacob.assign.algorithms.pattern;

import me.jacob.assign.algorithms.array.Problem1044;

public class Problem28 {

    public static void main(String[] args) {
        Problem28 p = new Problem28();
        System.out.println(p.strStr("leetcode", "leeto"));
    }

    public int strStr(String haystack, String needle) {
        //first create the LPS array
        int[] LPS = calculateLPS(needle);
        int i = 0;
        int j = 0;

        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                if (j == needle.length()) {
                    return i - j + 1;
                }
                i++;
            } else if (j == 0) {
                i++;
            } else {
                j = LPS[j - 1];
            }
        }

        return -1;
    }

    private int[] calculateLPS(String pattern) {
        int[] LPS = new int[pattern.length()];
        int prevLPS = 0;
        int i = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(prevLPS)) {
                prevLPS++;
                LPS[i] = prevLPS;
                i++;
            } else if (prevLPS == 0) {
                LPS[i] = 0;
                i++;
            } else {
                prevLPS = LPS[prevLPS - 1];
            }
        }

        return LPS;
    }
}
