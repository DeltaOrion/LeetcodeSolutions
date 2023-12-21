package me.jacob.assign.algorithms.bits;

import java.util.ArrayList;
import java.util.List;

public class Problem89 {

    public static void main(String[] args) {
        Problem89 p = new Problem89();
        System.out.println(-2147483648);
    }

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        //we want to loop from the current digit until 2 ^ n
        for (int i = 0; i < (1 << n); i++) {
            //we want to xor each digit of i, with the digit to the right
            //the easiest way to achieve this is to right shift i by 1, then xor with that
            //this approach might seem confusing at first but by definition the gray code is one
            //such that
            result.add(i ^ (i >> 1));
        }

        return result;
    }
}
