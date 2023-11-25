package me.jacob.assign.algorithms.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1916 {

    public static void main(String[] args) {
        Problem1916 p = new Problem1916();
        System.out.println(p.waysToBuildRooms(new int[]{-1,0,0,1,2}));
    }

    private int mod = 1000000007;

    public int waysToBuildRooms(int[] prevRoom) {
        int[] size = new int[prevRoom.length];
        Arrays.fill(size, 1);
        //add direct ancestors
        for (int i = prevRoom.length - 1; i >= 1; i--) {
            size[prevRoom[i]] += size[i];
        }

        //create a directed graph so we can run dfs
        List<Integer>[] children = new List[prevRoom.length];
        for (int i = 0; i < prevRoom.length; i++) {
            children[i] = new ArrayList<>();
        }

        for(int i=0; i < prevRoom.length;i++) {
            if (prevRoom[i] >= 0) {
                children[prevRoom[i]].add(i);
            }
        }

        size(children, size, 0);
        System.out.println(Arrays.toString(size));
        return (int) countOrderings(children, size, 0);
    }

    private int size(List<Integer>[] children, int[] size, int i) {
        if(children[i].size() == 0) {
            size[i] = 1;
            return 1;
        }

        int result = 1;
        for(int node : children[i]) {
            result += size(children, size, node);;
        }

        size[i] = result;
        return result;
    }

    private long countOrderings(List<Integer>[] graph, int[] size, int i) {
        long result = 1;
        int slots = size[i] - 1;
        for (int node : graph[i]) {
            result = (result % mod * nCrModPFermat(slots, size[node], mod) % mod) % mod;
            result = (result % mod * countOrderings(graph, size, node) % mod) % mod;
            slots = slots - size[node];
        }

        return result;
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
