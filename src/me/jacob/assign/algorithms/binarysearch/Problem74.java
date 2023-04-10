package me.jacob.assign.algorithms.binarysearch;

public class Problem74 {

    /**
     * You are given an m x n integer matrix matrix with the following two properties:
     *
     * Each row is sorted in non-decreasing order.
     * The first integer of each row is greater than the last integer of the previous row.
     * Given an integer target, return true if target is in matrix or false otherwise.
     *
     * You must write a solution in O(log(m * n)) time complexity.
     *
     *
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int highest = m*n - 1;
        int lowest = 0;
        while(lowest<=highest) {
            int middle = (lowest + highest)/2;
            int row = getRow(middle,n);
            int col = getCol(middle,n);

            if(matrix[row][col] < target) {
                lowest = middle + 1;
            } else if(matrix[row][col] > target) {
                highest = middle-1;
            } else {
                return true;
            }
        }
        return false;
    }

    private int getRow(int pos, int n) {
        return pos / n;
    }

    private int getCol(int pos, int n) {
        return pos % n;
    }
}
