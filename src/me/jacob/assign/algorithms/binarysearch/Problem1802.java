package me.jacob.assign.algorithms.binarysearch;

public class Problem1802 {

    public static void main(String[] args) {
        Problem1802 p = new Problem1802();
        int val = p.maxValue(6, 0, 14);
        System.out.println(val);
    }

    public int maxValue(int n, int index, int maxSum) {
        //first we want to fill the array with all 1's
        maxSum = maxSum - n;

        //now we want to find the extra
        int left = 0;
        int right = maxSum;
        while (left < right) {
            int middle = (left + right + 1) / 2;
            int comparator = getComparator(maxSum, index, n, middle);
            if (comparator == 1) {
                right = middle - 1;
            } else if (comparator == -1) {
                left = middle;
            } else {
                return middle + 1;
            }
        }

        return left + 1;
    }

    private int getComparator(int maxSum, int index, int n, int value) {
        int minLeft = Math.max(value - index, 0);
        int minRight = Math.max(value - (n - index - 1), 0);

        int range = value - 1;

        long sumBefore = (long) (((range - minLeft + 1) / 2.0) * (range + minLeft));
        long sumAfter = (long) (((range - minRight + 1) / 2.0) * (range + minRight));

        long totalSum = value + sumAfter + sumBefore;
        return Long.compare(totalSum, maxSum);
    }
}
