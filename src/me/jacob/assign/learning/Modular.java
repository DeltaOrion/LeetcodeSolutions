package me.jacob.assign.learning;

public class Modular {

    public static void main(String[] args) {
        System.out.println(modInverse(15, 19));
    }

    public static long pow(int a, int b) {
        if (b == 0) {
            return 1;
        }

        long pow = pow(a, b / 2);
        long res = pow * pow;
        if (b % 2 != 0) {
            res = res * a;
        }

        return res;
    }

    public static long modInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1) {
            return 0;
        }

        while (m > 0) {
            int q = a / m;
            int r = a % m;

            a = m;
            m = r;

            int t = x - y * q;
            x = y;
            y = t;
        }

        if (x < 0) {
            x += m0;
        }

        return x;
    }

    public static long powIterative(int a, int b) {
        long result = 1;
        long temp = a;
        int pow = 1;
        while (b > 0) {
            if (pow * 2 <= b) {
                temp = temp * temp;
                pow = pow * 2;
            } else {
                b = b - pow;
                result = result * temp;
                temp = a;
                pow = 1;

                if (b == 1) {
                    result = result * a;
                    b = 0;
                }
            }
        }

        return result;
    }

    private long modPow(long a, long b, int m) {
        if (m == 1) return 0;
        long result = 1;
        a = a % m;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % m;
            }
            b >>= 1;
            a = (a * a) % m;
        }
        return result;
    }
}
