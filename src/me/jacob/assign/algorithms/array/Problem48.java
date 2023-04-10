package me.jacob.assign.algorithms.array;

public class Problem48 {

    /**
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     *
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     */
    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix.length-1;
        while(right - left > 0) {
            for (int i = 0; i < right-left; i++) {
                //perform the 4 way swap
                swap(matrix, left, left+i, right - i, left);
                swap(matrix, right - i, left, right, right - i);
                swap(matrix, right, right - i, left + i, right);
            }
            right--;
            left++;
        }
    }

    public void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }
}
