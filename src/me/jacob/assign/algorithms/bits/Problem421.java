package me.jacob.assign.algorithms.bits;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Problem421 {

    public static void main(String[] args) {
        Problem421 p = new Problem421();
        p.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8});
    }

    public int findMaximumXOR(int[] nums) {
        int mask = 0;
        int result = 0;
        for (int i = 5; i >= 0; i--) {

            boolean set = false;
            boolean unset = false;
            boolean unsetMask = false;
            boolean setNotMask = false;

            for (int num : nums) {
                if ((num & mask) == 0) {
                    //if its not apart of the mask
                    if (((num >> i) & 1) == 0) {
                        unset = true;
                    } else if (mask == 0) {
                        set = true;
                    } else {
                        setNotMask = true;
                    }
                } else if (num >= mask) {
                    //apart of the mask.
                    //if its apart of the mask, and it is 1, then we need something not in the mask to equal 0
                    if (((num >> i) & 1) == 0) {
                        unsetMask = true;
                    } else {
                        set = true;
                    }
                }
            }

            if (set && unset) {
                mask = mask + (1 << i);
                result = result + (1 << i);
            } else if (unsetMask && setNotMask) {
                result = result + (1 << i);
            }
        }

        return result;
    }
}
