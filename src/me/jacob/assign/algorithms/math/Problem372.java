package me.jacob.assign.algorithms.math;

public class Problem372 {
    class Solution {
        public long pow(int a, int b, int m) {
            a = a % m;
            long res = 1;
            //binary exponentation
            while(b > 0) {
                if(b % 2 == 1) {
                    res = (res * a) % m;
                }

                a = (a * a)  % m;
                //b = b / 2
                b = b >> 1;
            }

            return res;
        }

        private int gcd(int a, int b) {
            while(b > 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }

            return a;
        }

        public int superPow(int a, int[] b) {
            int res = 1;
            int mod = 1337;
            int gcd = gcd(a, mod);

            //use traditional approach
            if(gcd != 1) {
                for (int digit : b) {
                    res = (int) (pow(res, 10, mod) * pow(a, digit, mod)) % mod;
                }

                return res;
            }

            int eulerTotient = 1140;
            int exponent = 0;
            for(int digit : b) {
                exponent = (exponent * 10 + digit) % eulerTotient;
            }

            if(exponent == 0) {
                exponent = eulerTotient;
            }

            return (int) pow(a, exponent, mod);
        }
    }
}
