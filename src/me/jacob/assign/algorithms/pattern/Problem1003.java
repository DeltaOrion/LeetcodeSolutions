package me.jacob.assign.algorithms.pattern;

public class Problem1003 {

    public static void main(String[] args) {
        Problem1003 p = new Problem1003();
        for(int i = 0;i<100;i++) {
            int clumbsy = p.clumsy(i);
            System.out.println(i + ": " + (clumbsy - i));
        }
    }

    private final int ADD_MULT = 0;
    private final int ADD = 1;
    private final int SUB_MULT = 2;

    public int clumsy(int n) {
        int state = 0;
        int result = 0;
        while(n >= 1) {
            int temp = 0;
            if(state == ADD_MULT) {
                temp += n;
                temp *= minus(n, 1);
                temp /= minus(n, 2);
                result += temp;
                n = n - 3;
                state = ADD;
            } else if(state == ADD) {
                result += n;
                n--;
                state = SUB_MULT;
            } else {
                temp += n;
                temp *= minus(n, 1);
                temp /= minus(n,2);
                result -= temp;
                n = n - 3;
                state = ADD;
            }
        }

        return result;
    }

    private int minus(int n, int i) {
        if(n - i <= 1) {
            return 1;
        }

        return n - i;
    }
}
