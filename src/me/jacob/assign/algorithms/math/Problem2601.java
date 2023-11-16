package me.jacob.assign.algorithms.math;

import java.util.ArrayList;
import java.util.List;

public class Problem2601 {

    public static void main(String[] args) {
        Problem2601 p = new Problem2601();
        p.primeSubOperation(new int[]{1});
    }

    public boolean primeSubOperation(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        //calculate primes
        boolean[] sieve = new boolean[max + 1];
        sieve[0] = sieve[1] = true;
        for (int i = 2; i * i <= max; i++) {
            if (!sieve[i]) {
                for (int j = 2 * i; j <= max; j += i) {
                    sieve[j] = true;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < sieve.length; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }

        int val = findSubtraction(primes, 0, nums[0]);
        if (val != -1) {
            nums[0] = nums[0] - primes.get(val);
        }

        for (int i = 1; i < nums.length; i++) {
            val = findSubtraction(primes, nums[i - 1], nums[i]);
            if (val == -1 && nums[i] <= nums[i - 1]) {
                return false;
            } else if (val != -1) {
                nums[i] = nums[i] - primes.get(val);
            }
        }

        return true;
    }

    private int findSubtraction(List<Integer> primes, int less, int val) {
        if (primes.size() == 0) {
            return -1;
        }

        int left = 0;
        int right = primes.size() - 1;
        int middle = (left + right) / 2;

        while (left <= right) {
            middle = (left + right) / 2;
            if (val - primes.get(middle) <= less) {
                right = middle - 1;
            } else {
                if (middle == primes.size() - 1) {
                    return middle;
                } else if (val - primes.get(middle + 1) <= less) {
                    return middle;
                } else {
                    left = middle + 1;
                }
            }
        }

        if (val - primes.get(middle) <= less) {
            return -1;
        } else {
            return middle;
        }
    }





}
