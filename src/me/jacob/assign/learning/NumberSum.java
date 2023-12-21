package me.jacob.assign.learning;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class NumberSum {

    public static void main(String[] args) {
        NumberSum sum = new NumberSum();
        sum.printCombinations(10);
    }

    private void printCombinations(int n) {
        combinationHelper(new ArrayList<>(), new boolean[n + 1], 0, n);
    }

    private void combinationHelper(List<Integer> current, boolean[] used, int sum, int target) {
        if (sum == target) {
            System.out.println(current);
            return;
        }

        List<Integer> added = new ArrayList<>();
        for (int i = 1; i <= target - sum; i++) {
            if (!used[i] && sum + i <= target) {
                used[i] = true;
                current.add(i);
                added.add(i);
                combinationHelper(current, used, sum + i, target);
                current.remove(current.size() - 1);
            }
        }

        for (int add : added) {
            used[add] = false;
        }
    }
}
