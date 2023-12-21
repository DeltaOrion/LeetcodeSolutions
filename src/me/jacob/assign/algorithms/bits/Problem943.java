package me.jacob.assign.algorithms.bits;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

public class Problem943 {

    public static void main(String[] args) {
        Problem943 p = new Problem943();
        System.out.println(p.shortestSuperstring(new String[]{"catg", "ctaagt", "gcta", "ttca", "atgcatc"}));
    }


    public String shortestSuperstring(String[] words) {
        int[][] LPS = new int[words.length][words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    LPS[i][j] = 0;
                } else {
                    LPS[i][j] = setDifferencePS(words[i], words[j]);
                }
            }
        }

        int[][] DP = new int[words.length][1 << words.length];
        int[][] paths = new int[words.length][1 << words.length];
        for (int[] arr : DP) {
            Arrays.fill(arr, -1);
        }

        //try all starting combinations
        int min = Integer.MAX_VALUE;
        int kMin = -1;
        for (int i = 0; i < words.length; i++) {
            int cost = words[i].length() + backtrack(LPS, DP, paths, words, i, (1 << words.length) - 1 - (1 << i));
            if (cost < min) {
                min = cost;
                kMin = i;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int k = kMin;
        int mask = (1 << words.length) - 1;
        while (mask > 0) {
            queue.offer(k);
            mask = mask - (1 << k);
            k = paths[k][mask];
        }

        StringBuilder result = new StringBuilder();
        int prev = -1;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (prev == -1) {
                result.append(words[node]);
            } else {
                //the suffix of the previous is the prefix of this one
                result.append(words[node].substring(LPS[prev][node]));
            }
            prev = node;
        }

        return result.toString();
    }

    private int backtrack(int[][] LPS, int[][] DP, int[][] paths, String[] words, int i, int mask) {
        if (mask == 0) {
            return 0;
        }

        if (DP[i][mask] != -1) {
            return DP[i][mask];
        }

        int min = Integer.MAX_VALUE;
        int kMin = -1;
        //if we have a node prev and we have a subset starting with k, it doesn't matter what is connecting to prev
        //we know that the subset starting with k will always be the same
        for (int k = 0; k < LPS.length; k++) {
            //check that k is available
            if (((1 << k) & mask) > 0) {
                int cost = words[k].length() + backtrack(LPS, DP, paths, words, k, mask - (1 << k)) - LPS[i][k];
                if (cost < min) {
                    min = cost;
                    kMin = k;
                }
            }
        }

        paths[i][mask] = kMin;
        return DP[i][mask] = min;
    }


    //suffix of A is equal to the prefix of B
    private int setDifferencePS(String a, String b) {
        //we can use a variation of KMP to work this out
        int N = Math.min(a.length(), b.length());
        int diff = Math.max(a.length() - b.length(), 0);
        int i = diff + 1;
        int prevLPS = 0;
        int[] LPS = new int[N];
        int[] bLPS = longestPrefixKMP(b);

        while (i < a.length()) {
            if (a.charAt(i) == b.charAt(prevLPS)) {
                prevLPS++;
                LPS[i - diff] = prevLPS;
                i++;
            } else if (prevLPS == 0) {
                i++;
            } else {
                prevLPS = bLPS[prevLPS - 1];
            }
        }

        return LPS[N - 1];
    }

    private int[] longestPrefixKMP(String s) {
        int[] LPS = new int[s.length()];
        int i = 1;
        int prevLPS = 0;

        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(prevLPS)) {
                prevLPS++;
                LPS[i] = prevLPS;
                i++;
            } else if (prevLPS == 0) {
                i++;
            } else {
                prevLPS = LPS[prevLPS - 1];
            }
        }

        return LPS;
    }
}
