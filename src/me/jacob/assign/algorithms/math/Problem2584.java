package me.jacob.assign.algorithms.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem2584 {

    public static void main(String[] args) {
        Problem2584 p = new Problem2584();
        System.out.println(p.findValidSplit(new int[]{25, 35, 1}));
    }

    private static int[] lPrime;

    public int findValidSplit(int[] nums) {
        if (lPrime == null) {
            int max = 100000;
            boolean[] sieve = new boolean[max + 1];
            lPrime = new int[max + 1];
            sieve[0] = sieve[1] = true;
            for (int i = 2; i * i <= max; i++) {
                if (!sieve[i]) {
                    for (int j = 2 * i; j <= max; j += i) {
                        sieve[j] = true;
                        lPrime[j] = i;
                    }
                }
            }
        }

        //calculate the total primes
        Map<Integer, Integer> totalPrimes = new HashMap<>();
        for (int num : nums) {
            List<Integer> primeFactors = factorPrime(lPrime, num);
            for (int factor : primeFactors) {
                totalPrimes.merge(factor, 1, Integer::sum);
            }
        }

        Map<Integer, Integer> windowPrimes = new HashMap<>();
        for (int i = 0; i < nums.length - 1; i++) {
            List<Integer> primeFactors = factorPrime(lPrime, nums[i]);
            for (int factor : primeFactors) {
                windowPrimes.merge(factor, 1, Integer::sum);
                Integer res = totalPrimes.get(factor);
                if (res == 1) {
                    totalPrimes.remove(factor);
                } else {
                    totalPrimes.put(factor, res - 1);
                }
            }

            boolean coPrime = true;
            for (int key : windowPrimes.keySet()) {
                if (totalPrimes.containsKey(key)) {
                    coPrime = false;
                    break;
                }
            }

            if (coPrime) {
                return i;
            }
        }

        return -1;
    }

    public List<Integer> factorPrime(int[] lPrime, int num) {
        List<Integer> primes = new ArrayList<>();
        while (num > 1) {
            int lowestPrime = lPrime[num];
            if (lowestPrime == 0) {
                primes.add(num);
                break;
            }

            while (num % lowestPrime == 0) {
                num = num / lowestPrime;
                primes.add(lowestPrime);
            }
        }

        return primes;
    }
}
