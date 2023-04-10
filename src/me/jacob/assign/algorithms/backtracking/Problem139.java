package me.jacob.assign.algorithms.backtracking;

import java.util.List;

public class Problem139 {

    /**
     * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
     *
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] DP = new boolean[s.length()+1];
        return wordBreak(s,wordDict,0,DP);
    }

    public boolean wordBreak(String s, List<String> wordDict, int curr, boolean[] DP) {
        if(DP[curr])
            return false;

        if(curr==s.length())
            return true;

        for(String word : wordDict) {
            if(matchWord(s,word,curr)) {
                if(wordBreak(s,wordDict,curr+word.length(),DP))
                    return true;
            }
        }

        DP[curr] = true;
        return false;
    }

    private boolean matchWord(String s, String word, int curr) {
        if(curr+word.length() > s.length())
            return false;

        for(int i=0;i<word.length();i++) {
            if(word.charAt(i) != s.charAt(curr+i)) {
                return false;
            }
        }
        return true;
    }
}
