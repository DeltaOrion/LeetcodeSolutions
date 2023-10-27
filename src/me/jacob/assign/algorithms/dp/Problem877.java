package me.jacob.assign.algorithms.dp;

public class Problem877 {

    public static void main(String[] args) {
        Problem877 p = new Problem877();
        var val = p.stoneGame(new int[]{3, 7, 2, 3});
        System.out.println(val);
    }

    public boolean stoneGame(int[] piles) {
        int[][] DPAlice = new int[piles.length][piles.length];
        int[][] DPBob = new int[piles.length][piles.length];

        for (int i = 0; i < piles.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (i == j) {
                    DPAlice[i][j] = DPBob[i][j] = piles[i];
                } else if (Math.abs(j - i) == 1) {
                    DPAlice[i][j] = Math.max(piles[i], piles[j]);
                    DPBob[i][j] = Math.min(piles[i], piles[j]);
                } else {
                    int optionI = piles[i] + DPBob[i - 1][j];
                    int optionJ = piles[j] + DPBob[i][j + 1];
                    if (optionI > optionJ) {
                        DPAlice[i][j] = optionI;
                        DPBob[i][j] = DPAlice[i - 1][j];
                    } else {
                        DPAlice[i][j] = optionJ;
                        DPBob[i][j] = DPAlice[i][j + 1];
                    }
                }
            }
        }

        return DPAlice[piles.length - 1][0] > DPBob[piles.length - 1][0];
    }

}
