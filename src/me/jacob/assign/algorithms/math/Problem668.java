package me.jacob.assign.algorithms.math;

public class Problem668 {

    public static void main(String[] args) {
        System.out.println(new Problem668().findKthNumber(10, 3, 8));
    }

    public int findKthNumber(int m, int n, int k) {
        int l = 1;
        int r = m * n;

        while (l <= r) {
            int middle = (l + r) / 2;
            int[] position = getPosition(middle, m, n);
            if (position[0] < k && k <= position[1]) {
                return middle;
            } else if (k <= position[0]) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }

        return 0;
    }

    private int[] getPosition(int i, int m, int n) {
        int[] result = new int[2];
        result[0] = Math.min(m, i - 1);
        result[1] = Math.min(m, i);
        for (int j = 2; j <= n; j++) {
            if (j * m < i) {
                result[0] += m;
                result[1] += m;
            } else {
                result[0] += (i - 1) / j;
                result[1] += i / j;
            }
        }

        return result;
    }
}
