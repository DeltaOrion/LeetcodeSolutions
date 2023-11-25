package me.jacob.assign.algorithms.math;

public class Problem866 {

    public static void main(String[] args) {
        System.out.println(new Problem866().primePalindrome(2));
    }

    public int primePalindrome(int n) {
        if (n == 1 || n == 0) {
            return 2;
        }
        //if n is even make it odd
        if ((n & 1) != 1 && n != 2) {
            n++;
        }

        for (int i = n; i <= 200000000; i += 2) {
            if (isPalindrome(i)) {
                if (isPrime(i)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isPalindrome(int i) {
        int rev = 0;
        int i0 = i;

        while (i > 0) {
            rev *= 10;
            rev += i % 10;
            i = i / 10;
        }

        return i0 == rev;
    }
}
