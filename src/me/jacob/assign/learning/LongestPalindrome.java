package me.jacob.assign.learning;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindromeManacherAlgorithm("cbbd"));
    }

    public static String longestPalindromeManacherAlgorithm(String s) {
        StringBuilder sPrime = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sPrime.append(s.charAt(i)).append("#");
        }

        int[] LPS = new int[sPrime.length()];
        //the center represents the center of the current palindrome.
        int center = 0;
        //the right side represents the right bound of the current palindrome
        int rightSide = 0;
        int maxI = 0;

        for (int i = 1; i < sPrime.length(); i++) {
            int mirror = 2 * center - i;
            //if the mirror is within the right bound of the current palindrome, we know it is
            //at least the size of the mirror palindrome. This makes sense because
            // 1. The right side of a palindrome is the reverse of the left side
            // 2. The length of the reverse of a palindrome, is the same as the length of a palindrome, because reversing a palindrome gives it itself.

            if (i < rightSide) {
                LPS[i] = Math.min(LPS[mirror], rightSide - i);
            }

            //expand around the center
            //it is important to take care here not to hit the outside.
            while (i - 1 - LPS[i] >= 0
                    && i + 1 + LPS[i] < sPrime.length()
                    && sPrime.charAt(i + 1 + LPS[i]) == sPrime.charAt(i - 1 - LPS[i])) {
                LPS[i]++;
            }

            //now if the right side of this palindrome bypasses the current palindrome, update the center accordingly.
            if (i + LPS[i] > rightSide) {
                center = i;
                rightSide = i + LPS[i];
            }

            if (LPS[i] > LPS[maxI]) {
                maxI = i;
            }
        }

        //now lets actually return the string
        return s.substring((maxI - LPS[maxI]) / 2, (maxI + LPS[maxI]) / 2);
    }

    public static String longestPalindromeExpandAroundCenter(String s) {
        int maxL = 0;
        int maxR = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 0;
            len = Math.max(expandAroundCenter(s, i, i + 1), len);
            len = Math.max(expandAroundCenter(s, i, i), len);

            if (len > (maxR - maxL + 1)) {
                maxR = i + (len) / 2;
                maxL = i - (len - 1) / 2;
            }
        }

        return s.substring(maxL, maxR + 1);
    }

    private static int expandAroundCenter(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        return r - l - 1;
    }
}
