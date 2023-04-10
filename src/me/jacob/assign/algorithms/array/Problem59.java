package me.jacob.assign.algorithms.array;

public class Problem59 {

    /**
     * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     */

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = matrix.length-1;
        int count = 1;

        while(left <= right) {
            for(int i=0;i<=right-left;i++) {
                matrix[left][left+i] = count;
                count++;
            }

            for(int i=1;i<=right-left;i++) {
                matrix[left+i][right] = count;
                count++;
            }

            for(int i=1;i<=right-left;i++) {
                matrix[right][right-i] = count;
                count++;
            }

            for(int i=1;i<right-left;i++) {
                matrix[right-i][left] = count;
                count++;
            }
            left++;
            right--;
        }
        return matrix;
    }
}
