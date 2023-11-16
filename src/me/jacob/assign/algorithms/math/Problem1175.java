package me.jacob.assign.algorithms.math;

import java.util.Arrays;

public class Problem1175 {

    int modulo = 1000000007;

    public int numPrimeArrangements(int n) {
        boolean[] sieve = new boolean[n+1];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= n; j += i) {
                    sieve[j] = false;
                }
            }
        }

        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (sieve[i]) {
                primes++;
            }
        }

        int notPrime = n - primes;
        return (int)(((long)factorial(notPrime) * factorial(primes)) % modulo);
    }

    private int factorial(int n) {
        long val = 1;
        for (int i = 2; i <= n; i++) {
            val = (val * i) % modulo;
        }
        return (int)val;
    }
}
