package me.jacob.assign.learning;

import java.util.*;

public class Factors {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(closestPrimes(19, 31)));
    }

    private static void findFactors(int n) {
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                System.out.println(i);
                System.out.println(n / i);
            }
        }
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static void findPrimeFactors(int n) {
        for (int i = 2; i * i < n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n = n / i;
                }

                System.out.println(i);
                if (n > 1) {
                    System.out.println(n);
                }
                break;
            }
        }
    }

    private static void findAllPrime(int n) {
        boolean[] sieve = new boolean[n];
        int[] lPrime = new int[n];

        //0 and 1 are prime numbers
        sieve[0] = sieve[1] = true;
        for (int i = 2; i * i < n; i++) {
            //if we've already marked out a number on the sieve, then we've already marked out its factors. We only care about
            //prime factors
            if (!sieve[i]) {
                //don't mark a prime as true, start from 2*i
                for (int j = 2 * i; j < n; j += i) {
                    sieve[j] = true;
                    if (lPrime[j] == 0) {
                        lPrime[j] = i;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (!sieve[i]) {
                System.out.println("Is Prime: " + i);
            }
        }
    }

    private static void allPrimeFactors(int n) {
        boolean[] sieve = new boolean[n + 1];
        int[] lPrime = new int[n + 1];

        //0 and 1 are prime numbers
        sieve[0] = sieve[1] = true;
        for (int i = 2; i * i <= n; i++) {
            //if we've already marked out a number on the sieve, then we've already marked out its factors. We only care about
            //prime factors
            if (!sieve[i]) {
                //don't mark a prime as true, start from 2*i
                for (int j = 2 * i; j <= n; j += i) {
                    sieve[j] = true;
                    if (lPrime[j] == 0) {
                        lPrime[j] = i;
                    }
                }
            }
        }

        //now that we have lprime we can combine our techniques and work out all primes
        List<Integer> primeFactors = new ArrayList<>();
        while (n > 1) {
            int lowestPrime = lPrime[n];
            if (lowestPrime == 0) {
                primeFactors.add(n);
                break;
            }

            while (n % lowestPrime == 0) {
                primeFactors.add(lowestPrime);
                n = n / lowestPrime;
            }
        }

        System.out.println(primeFactors);
    }

    public static int[] closestPrimes(int left, int right) {
        boolean[] sieve = new boolean[right + 1];
        sieve[0] = true;
        sieve[1] = true;
        for (int i = 0; i * i < right; i++) {
            if (!sieve[i]) {
                for (int j = i * 2; j < right; j += i) {
                    sieve[j] = true;
                }
            }
        }

        int i = left;
        int j = left;
        int iMin = -1;
        int jMin = -1;
        while (j < right) {
            if (!sieve[j]) {
                //we found a prime
                if (i != j && !sieve[i]) {
                    if (j - i < jMin - iMin || iMin == -1) {
                        jMin = j;
                        iMin = i;
                    }

                    if (jMin - iMin == 2) {
                        return new int[]{iMin, jMin};
                    }
                }

                i = j;
            }

            j++;
        }

        return new int[]{iMin, jMin};
    }
}
