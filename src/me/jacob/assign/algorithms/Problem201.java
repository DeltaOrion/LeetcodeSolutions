package me.jacob.assign.algorithms;

public class Problem201 {

    public int rangeBitwiseAnd(int left, int right) {

        int count = 0;
        while(left!=right) {
            left = left >> 1;
            right = right >> 1;
            count+=1;
        }

        return left << count;
    }
}
