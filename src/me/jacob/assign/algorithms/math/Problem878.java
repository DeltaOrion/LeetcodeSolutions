package me.jacob.assign.algorithms.math;

public class Problem878 {

    public static void main(String[] args) {
        Problem878 p = new Problem878();
        System.out.println(p.nthMagicalNumber(5, 5, 7));
    }

    private final int MODULO = 1000000007;

    public int nthMagicalNumber(int n, int a, int b) {
        long lcm = lcm(a, b);
        long numberOfA = ((lcm / a));
        long numberOfB = ((lcm / b));

        long groupSize = (numberOfA + numberOfB) - 1; //how big is a group
        long groupIndex = (n - 1) / groupSize; //which group are we in

        long i = 1;
        long j = 1;
        long magicalNeeded = n - groupIndex * groupSize; //which magical number in the group are we getting
        long ans = 0;
        while(magicalNeeded > 0) {
            if(i * a < b * j) {
                ans = i * a;
                i++;
            } else {
                ans = b * j;
                j++;
            }

            magicalNeeded--;
        }

        return (int) ((lcm * groupIndex + ans) % MODULO);
    }

    private int lcm(int a, int b) {
        long lcm = ((long)a * b) / gcd(a, b);
        return (int)(lcm % MODULO);
    }


    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
