package me.jacob.assign.algorithms.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem30 {

    public static void main(String[] args) {
        Problem30 p = new Problem30();
        var res = p.findSubstring("aaa", new String[]{"a","a"});
        System.out.println(res);
    }

    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.merge(word, 1, Integer::sum);
        }

        int wordLength = words[0].length();
        int subStringLength = words.length * wordLength;

        List<Integer> result = new ArrayList<>();
        Map<Integer, String> wordIndexCache = new HashMap<>();
        for (int x = 0; x < wordLength; x++) {
            Map<String, Integer> windowFreq = new HashMap<>();
            int i = x;
            int j = x;
            while (j < s.length() - wordLength + 1) {
                //1. get the word
                String word = wordIndexCache.get(j);
                if (word == null) {
                    word = getWord(s, j, wordLength);
                }

                if (wordFreq.containsKey(word)) {
                    wordIndexCache.put(j, word);
                    windowFreq.merge(word, 1, Integer::sum);
                } else {
                    windowFreq.clear();
                    i = j;
                }

                j += wordLength;

                if (j - i + 1 > subStringLength) {
                    if (wordFreq.equals(windowFreq)) {
                        result.add(i);
                    }

                    String leftWord = wordIndexCache.get(i);
                    Integer res = windowFreq.get(leftWord);
                    if (res != null && res == 1) {
                        windowFreq.remove(leftWord);
                    } else if (res != null) {
                        windowFreq.put(leftWord, res - 1);
                    }

                    i += wordLength;
                }
            }
        }

        return result;
    }

    private String getWord(String s, int j, int wordLength) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wordLength; i++) {
            stringBuilder.append(s.charAt(i + j));
        }

        return stringBuilder.toString();
    }

}
