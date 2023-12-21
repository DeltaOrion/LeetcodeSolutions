package me.jacob.assign.algorithms.bits;

public class Problem318 {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] bitmasks = new int[n];

        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                bitmasks[i] |= 1 << (c - 'a');
            }
        }

        int maxProduct = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((bitmasks[i] & bitmasks[j]) == 0) {
                    int tempProduct = words[i].length() * words[j].length();
                    maxProduct = Math.max(maxProduct, tempProduct);
                }
            }
        }

        return maxProduct;
    }
}
