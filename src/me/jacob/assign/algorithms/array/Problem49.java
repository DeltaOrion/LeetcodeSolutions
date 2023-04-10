package me.jacob.assign.algorithms.array;

import java.util.*;

public class Problem49 {

    /**
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        //frequency fingerprint
        Map<String, List<String>> anagrams = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for(int i=0;i<strs.length;i++) {
            String fingerprint = getFingerprint(strs[i]);
            List<String> list = anagrams.get(fingerprint);
            if(list==null) {
                list = new ArrayList<>();
                anagrams.put(fingerprint,list);
                result.add(list);
            }

            list.add(strs[i]);
        }
        return result;
    }

    private String getFingerprint(String str) {
        char[] arr=str.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
