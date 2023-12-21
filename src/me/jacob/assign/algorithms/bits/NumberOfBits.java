package me.jacob.assign.algorithms.bits;

import java.util.Arrays;

public class NumberOfBits {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 4, 5};
        NumberOfBits n = new NumberOfBits();
        System.out.println(n.numberMissing(arr));
    }

    public int numberMissing(int[] numbers) {
        int xor = 0;
        //why A ^ A = 0
        //so cancel out all numbers which are not the missing number
        //this is literally the same problem as all numbers appear twice except 1.
        for (int num : numbers) {
            xor = xor ^ num;
        }

        for (int i = 0; i <= numbers.length; i++) {
            xor = xor ^ i;
        }

        return xor;
    }

    public int[] countNumberOfBits(int n) {
        int[] count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            count[i] = count[i & (i - 1)] + 1;
        }

        return count;
    }

    public int[] countBitsFrequency(int n) {
        int[] count = new int[leftMostSetBit(n)];
        for (int i = count.length - 1; i >= 0; i--) {
            count[i] = (n >> (count.length - i)) * (1 << count.length - 1 - i);
            if ((n & (1 << count.length - 1 - i)) != 0) {
                count[i] += n % (1 << count.length - 1);
            }
        }

        count[0]++;

        return count;
    }

    private int leftMostSetBit(int n) {
        int position = 0;
        while (n > 0) {
            n = n >> 1;
            position++;
        }

        return position;
    }
}
