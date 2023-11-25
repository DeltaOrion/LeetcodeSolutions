package me.jacob.assign.algorithms.math;

import java.util.HashMap;
import java.util.Map;

public class Problem2514 {

    private int mod = 1000000007;

    public int countAnagrams(String s) {
        long res = 1;
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                res = (res * getAnagrams(count)) % mod;
                count.clear();
            } else {
                count.merge(s.charAt(i), 1, Integer::sum);
            }
        }

        res = res * getAnagrams(count) % mod;
        return (int) res;
    }

    private long modInverse(long a, long m) {
        long x = 1;
        long y = 0;
        long m0 = m;
        while (m > 0) {
            long q = a / m;
            long r = a % m;

            //gcd
            a = m;
            m = r;

            //we want ax - by
            long t = x - q * y;
            x = y;
            y = t;
        }

        if (x < 0) {
            x += m0;
        }

        return x;
    }

    private int getAnagrams(Map<Character, Integer> count) {
        Map<Integer, Integer> inverseCount = new HashMap<>();
        int sum = 0;
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            inverseCount.merge(entry.getValue(), 1, Integer::sum);
            sum += entry.getValue();
        }

        long divisor = 1;
        long fact = 1;
        for (int i = 2; i <= sum; i++) {
            fact = (fact * i % mod) % mod;
            Integer val = inverseCount.get(i);
            if (val != null) {
                divisor = (divisor * modPow(fact, val, mod)) % mod;
            }
        }

        return (int) ((fact * modInverse(divisor, mod)) % mod);
    }

    private long modPow(long a, long b, long m) {
        long res = 1;
        if(m == 1) {
            return 0;
        }
        a = a % m;
        while(b > 0) {
            if((b & 1) == 1) {
                res = (res * a) % m;
            }

            a = (a * a) % m;
            b = b >> 1;
        }

        return res;
    }
}
