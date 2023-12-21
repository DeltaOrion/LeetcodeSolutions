package me.jacob.assign.algorithms.string;

public class DeleteOperationTwoStrings {

    public static void main(String[] args) {
        DeleteOperationTwoStrings p = new DeleteOperationTwoStrings();
        p.minDistance("leetcode", "eetc");
    }

    private final static int WORD_ONE = 0;
    private final static int WORD_TWO = 1;
    private final static int NOTHING = 3;

    public int minDistance(String word1, String word2) {
        int[][] DP = new int[word1.length() + 1][word2.length() + 1];
        int[][] R = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            DP[i][0] = i;
            R[i][0] = WORD_ONE;
        }

        for (int i = 0; i <= word2.length(); i++) {
            DP[0][i] = i;
            R[0][i] = WORD_TWO;
        }

        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    DP[i + 1][j + 1] = DP[i][j];
                    R[i + 1][j + 1] = NOTHING;
                } else {
                    if (DP[i + 1][j] < DP[i][j + 1]) {
                        //deleted a word from word 2
                        DP[i + 1][j + 1] = DP[i + 1][j] + 1;
                        R[i + 1][j + 1] = WORD_TWO;
                    } else {
                        DP[i + 1][j + 1] = DP[i][j + 1] + 1;
                        R[i + 1][j + 1] = WORD_ONE;
                    }
                }
            }
        }

        int i = word1.length();
        int j = word2.length();
        while (i > 0 || j > 0) {
            if (R[i][j] == WORD_ONE) {
                System.out.println("Delete " + word1.charAt(i - 1) + " (Index " + i + ") from " + word1);
                i--;
            } else if (R[i][j] == WORD_TWO) {
                System.out.println("Delete " + word2.charAt(j - 1) + " (Index " + j + ") from " + word2);
                j--;
            } else {
                i--;
                j--;
            }
        }

        return DP[word1.length()][word2.length()];
    }
}
