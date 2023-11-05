package me.jacob.assign.algorithms;

import java.util.*;

public class Problem395 {

    public static void main(String[] args) {
        Problem395 problem395 = new Problem395();
        int val = problem395.longestSubstring("aa", 1);
        System.out.println(val);
    }

    private int max = 0;

    public int longestSubstring(String s, int k) {
        //1. Count the unique characters
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            chars.add(s.charAt(i));
        }

        int numberOfUnique = chars.size();
        int max = 0;
        for (int i = 1; i <= numberOfUnique; i++) {
            //run a sliding window
            Map<Character, Integer> freqMap = new HashMap<>();
            int distinct = 0;
            int greater = 0;
            int l = 0;
            int r = 0;
            while (r < s.length()) {
                while (!freqMap.containsKey(s.charAt(r)) && freqMap.size() == i) {
                    Integer freq = freqMap.get(s.charAt(l));
                    if (freq == 1) {
                        freqMap.remove(s.charAt(l));
                        distinct--;
                        if (k == 1) {
                            greater--;
                        }
                    } else {
                        if (freq == k) {
                            greater--;
                        }
                        freqMap.put(s.charAt(l), freq - 1);
                    }

                    l++;
                }

                //add the newest one in
                Integer freq = freqMap.get(s.charAt(r));
                if (freq == null) {
                    freqMap.put(s.charAt(r), 1);
                    distinct++;
                    if (k == 1) {
                        greater++;
                    }
                } else {
                    if (freq == k - 1) {
                        greater++;
                    }
                    freqMap.put(s.charAt(r), freq + 1);
                }

                if (distinct == greater) {
                    max = Math.max(max, r - l + 1);
                }

                r++;
            }
        }

        return max;
    }


    public int longestSubstringDivideAndConquer(String s, int k) {
        helper(s, 0, s.length(), k);
        return max;
    }

    private void helper(String s, int i, int j, int k) {
        if (j - i < k) {
            return;
        }

        int[] frequency = new int[26];
        for (int x = i; x < j; x++) {
            frequency[s.charAt(x) - 'a']++;
        }

        boolean found = true;
        for (int freq : frequency) {
            if (freq > 0 && freq < k) {
                found = false;
                break;
            }
        }

        if (found) {
            max = Math.max(max, j - i);
            return;
        }


        //split the string for any character not greater
        int l = i;
        int r = i;
        while (r < j) {
            if (frequency[s.charAt(r) - 'a'] < k) {
                helper(s, l, r, k);
                l = r + 1;
            }

            r++;
        }

        helper(s, l, r, k);
    }
}
