package me.jacob.assign.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Problem119 {

    /**
     * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        long nck = 1;
        for(int k = 0; k <= rowIndex; k++){
            row.add((int)nck);
            nck = (nck * (rowIndex - k)) / (k+1);
        }
        return row;
    }

}
