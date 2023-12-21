package me.jacob.assign.learning;

import java.util.Locale;

public class BitManipulation {

    public static void main(String[] args) {
        BitManipulation m = new BitManipulation();
        m.printDigitsBinary1(13);
        System.out.println();
        m.ithDigitSet(13, 0);
        m.numberOfBinaryDigits(13);
        System.out.println("10000 is a power of 10?: " + m.isPowerOf10(10000));
        System.out.println("29000 is a power of 10?: " + m.isPowerOf10(29000));
        System.out.println("8 is a power of 2?: " + m.isPowerOf2(8));
        System.out.println("20 is a power of 2?: " + m.isPowerOf2(20));

        //turn on the 5th bit to convert a character to lower case
        System.out.println((char) ('A' | (1 << 5)));

        //turn OFF the 5th bit to convert to lower case
        //the number 95 is a bitmask for 1011111 so by running an AND with this bitmask we keep everything on apart from
        //the 5th digit.
        System.out.println((char) (95 & 'a'));

        System.out.println("Nearest Power of 2 of 97: " + m.SetI(97, 3));
        System.out.println("Negative 97 = " + m.negative(97));
    }

    private int negative(int n) {
        return ~n + 1;
    }

    private int SetI(int n, int i) {
        int mask = (1 << i) - 1;
        return n | mask;
    }

    private boolean isPowerOf2(int x) {
        return (x & (x - 1)) == 0;
    }

    private void printDigitsBinary1(int n) {
        if (n == 0) {
            System.out.println("Printing digits");
            return;
        }

        int digit = n & 1;
        printDigitsBinary1(n >> 1);
        System.out.print(digit + ", ");
    }

    private void numberOfBinaryDigits(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n >> 1;
        }

        System.out.println("Number of Binary Digits: " + count);
    }

    private boolean isPowerOf10(int x) {
        x = x - 1;
        while (x > 0) {
            if (x % 10 != 9) {
                return false;
            }

            x = x / 10;
        }

        return true;
    }

    private void ithDigitSet(int n, int i) {
        System.out.println("Is the " + i + "th digit of " + n + " set?");
        if ((n & (1 << i)) != 0) {
            System.out.println("Set");
        } else {
            System.out.println("Not Set");
        }
        System.out.println("-----------");
    }

    private void printDigitsBase10(int n) {
        if (n == 0) {
            return;
        }
        int digit = n % 10;
        printDigitsBase10(n / 10);
        System.out.println(digit + ", ");
    }
}
