package me.jacob.assign.algorithms.string;

public class LevenstheinDistance {

    private final static int DELETE = 0;
    private final static int INSERT = 1;
    private final static int REPLACE = 2;
    private final static int NOTHING = 3;

    public static void main(String[] args) {
        LevenstheinDistance d = new LevenstheinDistance();
        d.minDistance("zoologicoarchaeologist", "zoogeologist");
    }

    public int minDistance(String word1, String word2) {
        int[][] DP = new int[word1.length() + 1][word2.length() + 1];
        int[][] R = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            DP[i][0] = i;
            R[i][0] = DELETE;
        }

        for (int j = 0; j <= word2.length(); j++) {
            DP[0][j] = j;
            R[0][j] = INSERT;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    DP[i + 1][j + 1] = DP[i][j];
                    R[i + 1][j + 1] = NOTHING;
                } else {
                    DP[i + 1][j + 1] = Math.min(DP[i][j], Math.min(DP[i + 1][j], DP[i][j + 1])) + 1;
                    if (DP[i + 1][j + 1] == DP[i][j] + 1) {
                        R[i + 1][j + 1] = REPLACE;
                    } else if (DP[i + 1][j + 1] == DP[i][j + 1] + 1) {
                        R[i + 1][j + 1] = DELETE;
                    } else {
                        R[i + 1][j + 1] = INSERT;
                    }
                }
            }
        }

        int i = word1.length();
        int j = word2.length();
        String w1 = word1;
        while (i > 0 && j > 0) {
            String newWord;
            if (R[i][j] == REPLACE) {
                newWord = replace(word1, i - 1, word2.charAt(j - 1));
                System.out.println(word1 + " -> " + newWord + " (Replace " + word1.charAt(i - 1) + " with " + word2.charAt(j - 1) + ")");
                i--;
                j--;
            } else if (R[i][j] == INSERT) {
                newWord = word1 + word2.charAt(j - 1);
                System.out.println(word1 + " -> " + newWord + " (Insert " + word2.charAt(j - 1) + ")");
                j--;
            } else if (R[i][j] == DELETE) {
                newWord = delete(word1, i - 1);
                System.out.println(word1 + " -> " + newWord + " (Remove " + word1.charAt(i - 1) + ")");
                i--;
            } else {
                i--;
                j--;
                newWord = word1;
            }

            word1 = newWord;
        }

        System.out.println("Total Distance: " + DP[w1.length()][word2.length()]);
        return DP[w1.length()][word2.length()];
    }

    private String delete(String s, int index) {
        char[] arr = s.toCharArray();
        char[] replace = new char[arr.length - 1];
        for (int i = 0; i < index; i++) {
            replace[i] = arr[i];
        }

        for (int i = index + 1; i < arr.length; i++) {
            replace[i - 1] = arr[i];
        }

        return new String(replace);
    }

    private String replace(String s, int index, char replace) {
        char[] arr = s.toCharArray();
        arr[index] = replace;
        return new String(arr);
    }
}
