package me.jacob.assign.algorithms.array;

import java.util.*;

public class Problem1044 {

    public static void main(String[] args) {
        Problem1044 p = new Problem1044();
        System.out.println(p.longestDupSubstring("zxcvdqkfawuytt"));
    }

    public String longestDupSubstring(String s) {
        int left = 1;
        int right = s.length() - 1;
        int max = 0;
        int maxI = 0;
        int base = 31;
        int m = 1000000007;

        Map<Long, List<Integer>> rollingHashes = new HashMap<>();
        while (left <= right) {
            //let k be the width of the sliding window
            int k = (left + right) / 2;
            long hash = 0;
            long exponent = 1;
            boolean found = false;

            for (int i = 0; i < k; i++) {
                hash = (hash * base + (s.charAt(i) - 'a')) % m;
                if (i < k - 1) {
                    exponent = (exponent * base) % m;
                }
            }

            List<Integer> l = new ArrayList<>();
            l.add(0);
            rollingHashes.put(hash, l);

            for (int i = k; i < s.length(); i++) {
                hash = (base * (hash + m - (exponent * (s.charAt(i - k) - 'a')) % m) % m + (s.charAt(i) - 'a')) % m;

                List<Integer> list = rollingHashes.computeIfAbsent(hash, k1 -> new ArrayList<>());
                if (list.size() >= 1) {
                    //check if strings are equal
                    if (checkList(list, s, i - k + 1, k)) {
                        found = true;
                        max = k;
                        maxI = i - k + 1;
                        break;
                    }
                }
                list.add(i - k + 1);
            }

            if (found) {
                //if we found one at the current length, there always exists below.
                left = k + 1;
            } else {
                //if we couldn't find one of this length, there is definitely not one higher
                right = k - 1;
            }
        }

        return s.substring(maxI, maxI + max);
    }

    private boolean checkList(List<Integer> list, String s, int i, int k) {
        for (int index : list) {
            boolean stringEqual = true;
            for (int j = 0; j < k; j++) {
                if (s.charAt(index + j) != s.charAt(i + j)) {
                    stringEqual = false;
                    break;
                }
            }

            if (stringEqual) {
                return true;
            }
        }

        return false;
    }
}
