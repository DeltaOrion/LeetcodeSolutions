package me.jacob.assign.algorithms.dp;

public class Problem264 {

    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;

        int index2 = 0;
        int index3 = 0;
        int index5 = 0;

        for(int i=1;i<n;i++) {
            int min = Math.min(Math.min(ugly[index2] * 2, ugly[index3] * 3), ugly[index5] * 5);
            if(min == ugly[index2] * 2) {
                ugly[i] = ugly[index2++] * 2;
            }

            if(min == ugly[index3] * 3) {
                ugly[i] = ugly[index3++] * 3;
            }

            if (min == ugly[index5] * 5) {
                ugly[i] = ugly[index5++] * 5;
            }
        }

        return ugly[n-1];
    }
}
