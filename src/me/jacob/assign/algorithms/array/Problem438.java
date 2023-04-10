package me.jacob.assign.algorithms.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem438 {

    /**
     * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
     */

    public static List<Integer> findAnagrams(String s, String p) {
        //establish fingers
        int tSumFinger = 0;
        int pSumFinger = 0;

        int[] countsT = new int[26];
        int[] countsP = new int[26];
        if(s.length() < p.length())
            return Collections.emptyList();

        List<Integer> solution = new ArrayList<>();

        //form initial fingers
        for(int i=0;i<p.length();i++) {
            tSumFinger += s.charAt(i);
            pSumFinger += p.charAt(i);

            countsT[s.charAt(i)-'a']++;
            countsP[p.charAt(i)-'a']++;
        }

        //begin rolling hash
        for(int i=0;i<=s.length()-p.length();i++) {
            if(tSumFinger == pSumFinger) {
                //actually check if this is a match
                if(foundMatch(countsP,countsT))
                    solution.add(i);
            }

            tSumFinger -= s.charAt(i);
            countsT[s.charAt(i)-'a']--;

            if(i<s.length()-p.length()) {
                char c = s.charAt(i + p.length());
                tSumFinger += c;
                countsT[c-'a']++;
            }
        }
        return solution;
    }

    private static boolean foundMatch(int[] countsP, int[] countsT) {
        for(int j=0;j<countsP.length;j++) {
            if(countsP[j]!=countsT[j])
                return false;
        }
        return true;
    }
}
