package me.jacob.assign.algorithms.array;

public class Problem415 {

    /**
     * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
     *
     * You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
     */

    public String addStrings(String num1, String num2) {

        int i = num1.length()-1;
        int j = num2.length()-1;
        int carry = 0;
        StringBuilder result = new StringBuilder();

        while(i>=0 || j>=0) {
            int add = 0;
            if(i>=0) {
                add += (num1.charAt(i) - '0');
            }

            if(j>=0) {
                add += (num2.charAt(j) - '0');
            }

            add += carry;
            if(add>=10) {
                add = add-10;
                carry = 1;
            } else {
                carry = 0;
            }

            result.append(add);
            i--;
            j--;
        }

        if(carry!=0) {
            result.append('1');
        }

        return result.reverse().toString();
    }
}
