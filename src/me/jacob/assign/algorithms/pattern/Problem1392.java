package me.jacob.assign.algorithms.pattern;

public class Problem1392 {

    public String longestPrefix(String s) {
        return longestPrefixKMP(s);
    }

    private String longestPrefixRabinKarp(String s) {
        int base = 31;
        int mod = 100000007;
        long exponent = 1;
        long lHash = 0;
        long rHash = 0;
        int longest = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            lHash = (lHash * base + (s.charAt(i) - 'a')) % mod;
            rHash = (rHash + ((s.charAt(s.length() - 1 - i) - 'a') * exponent) % mod) % mod;

            if (lHash == rHash) {
                longest = i + 1;
            }

            exponent = (exponent * base) % mod;
        }

        return s.substring(0, longest);
    }

    private String longestPrefixKMP(String s) {
        int[] LPS = new int[s.length()];
        int i = 1;
        int prevLPS = 0;

        while(i < s.length()) {
            if(s.charAt(i) == s.charAt(prevLPS)) {
                prevLPS++;
                LPS[i] = prevLPS;
                i++;
            } else if(prevLPS == 0) {
                prevLPS = 0;
                i++;
            } else {
                //1. We know that the prevLPS characters - 1 is equal to the prevLPS characters - 1 before the current i, this is because
                //   we incremented the prevLPS last round to check if we could extend the prefix
                //2. Unfortunately we could not extend the prevLPS last round
                //3. Thus, as we know that the last prevLPS characters are equal to the current suffix's prevLPS characters, the best thing
                //   we can hope to do is connect to the prevLPS's prefix.
                //4. Example, suppose we have ABCABCXXXXXXXXXXXABCABCA
                //5. When we hit the final A in this string WANT to connect it to the X ideally but we cant!
                //6. We know that the first ABCABC and ABCABC are identical, because thats how we define the prevLPS
                //7. With ABCABC, we know that the first 3 are equal to the last 3
                //8. This means the very first ABC in the first occurance of the string, is equal to the very last ABC in the last occurance of the string string
                //9. This means we can continue on the prefix suffix from there.
                //10. This is a clear application of dynamic programming, we memoise the LPS of each smaller array, so that if we fail, we can see where to connect to, by using the substring of the current prefix
                prevLPS = LPS[prevLPS - 1];
            }
        }

        return s.substring(0, LPS[LPS.length - 1]);
    }
}
