package me.jacob.assign.algorithms;

import java.util.HashSet;
import java.util.Set;

public class Problem202 {

    /**
     * Write an algorithm to determine if a number n is happy.
     *
     * A happy number is a number defined by the following process:
     *
     * Starting with any positive integer, replace the number by the sum of the squares of its digits.
     * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
     * Those numbers for which this process ends in 1 are happy.
     * Return true if n is a happy number, and false if not.
     */

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while(true) {
            //looping endlessly in cycle;
            if(seen.contains(n))
                return false;

            seen.add(n);
            if(n==1) {
                return true;
            }

            n = happySum(n);
        }
    }

    private int happySum(int n) {
        int squareSum = 0;
        while (n!=0) {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }
        return squareSum;
    }

}
