package me.jacob.assign.algorithms.math;

import java.util.HashMap;
import java.util.Map;

public class Problem1735 {

    public static void main(String[] args) {
        Problem1735 p = new Problem1735();
        System.out.println(p.waysToFillArray(new int[][] {{5,1}}));
    }

    private int modulo = 1000000007;

    public int[] waysToFillArray(int[][] queries) {

        int[] res = new int[queries.length];

        int count = 0;
        for (int[] query : queries) {
            int n = query[0];
            int k = query[1];

            if(k == 1) {
                res[count++] = 1;
                continue;
            }

            Map<Integer, Integer> primeFactors = new HashMap<>();

            while (k > 0) {
                int lowestPrime = lowestPrime(k);
                if (lowestPrime == 0) {
                    primeFactors.put(k, 1);
                    break;
                }

                while (k % lowestPrime == 0) {
                    primeFactors.merge(lowestPrime, 1, Integer::sum);
                    k = k / lowestPrime;
                }
            }

            long val = 1;
            for (Map.Entry<Integer, Integer> factor : primeFactors.entrySet()) {
                val = mul(nCrModPFermat(factor.getValue() + (n - 1), (n - 1), modulo), val, modulo);
            }

            res[count] = (int) (val);
            count++;
        }

        return res;
    }

    private int lowestPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return i;
            }
        }

        return 0;
    }

    public static long power(long x, int y, int p) {
        long res = 1; // Initialize result

        x = x % p; // Update x if it is more than or equal to p

        while (y > 0) {
            // If y is odd, multiply x with result
            if ((y & 1) == 1)
                res = (res * x) % p;

            // y must be even now
            y = y >> 1; // y = y/2
            x = (x * x) % p;
        }
        return res;
    }

    // Returns n^(-1) mod p
    public static long modInverse(long n, int p) {
        return power(n, p - 2, p);
    }

    public static long mul(long x, long y, int p) {
        return x * 1L * y % p;
    }

    public static long divide(long x, long y, int p) {
        return mul(x, modInverse(y, p), p);
    }

    // Returns nCr % p using Fermat's little theorem.
    public static long nCrModPFermat(long n, long r, int p) {
        // If n<r, then nCr should return 0
        if (n < r)
            return 0;

        // Base case
        if (r == 0)
            return 1;

        // if n-r is less calculate nCn-r
        if (n - r < r)
            return nCrModPFermat(n, n - r, p);

        // Fill factorial array so that we can find all factorial of r, n and n-r
        long res = 1;
        // keep multiplying numerator terms and dividing denominator terms in res
        for (long i = r; i >= 1; i--)
            res = divide(mul(res, n - i + 1, p), i, p);
        return res;
    }
}
