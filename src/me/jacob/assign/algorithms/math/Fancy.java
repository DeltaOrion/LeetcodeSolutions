package me.jacob.assign.algorithms.math;

import java.util.ArrayList;
import java.util.List;

class Fancy {

    private List<Long> values;
    private long multiplier;
    private long increment;

    private int mod = 1000000007;

    public Fancy() {
        values = new ArrayList<>();
        multiplier = 1;
        increment = 0;
    }

    //calculate the modular multiplicative inverse
    private long modPow(long a, long b, int m) {
        if (m == 1) return 0;
        long result = 1;
        a = a % m;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % m; //dump the acumulation onto result each time.
            }
            b >>= 1;
            a = (a * a) % m; //accumulate on a
        }
        return result;
    }

    public void append(int val) {
        //whenever we append we want to reverse the changes from the previous add and multiplication
        //luckily both can be inverted in mod arithematic quite easily!
        long res = (val - increment) % mod;
        if (res < 0) res += mod;
        //this only works because we know that
        // - mod is the largest prime integer
        // - val is an integer meaning it must be coprime with mod
        // this means that fermats little theorem works and the multiplicative inverse
        // is a^phi(n) - 1 = a^n - 2 (totient for primes is n - 1)/
        res = (res % mod * modPow(multiplier, mod - 2, mod) % mod) % mod;
        values.add(res);
    }

    public void addAll(int inc) {
        increment = (increment + inc % mod) % mod; //work out the increment
    }

    public void multAll(int m) {
        multiplier = (multiplier * m % mod) % mod; //work out the multiplier
        increment = (increment * m % mod) % mod;
    }

    public int getIndex(int idx) {
        if(idx >= values.size()) {
            return -1;
        }

        return (int) ((values.get(idx) * multiplier) % mod + increment) % mod;
    }
}