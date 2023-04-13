package me.jacob.assign.algorithms.datastructures;

import java.util.Stack;

public class Problem946 {

    public static void main(String[] args) {
        Problem946 p = new Problem946();
        int[] pushed = new int[]{1,2,0};
        int[] popped = new int[]{2,1,0};

        System.out.println(p.validateStackSequences(pushed,popped));
    }

    /**
     * Given two integer arrays pushed and popped each with distinct values, return true if this could have been the result
     * of a sequence of push and pop operations on an initially empty stack, or false otherwise.
     *
     * 1 <= pushed.length <= 1000
     * 0 <= pushed[i] <= 1000
     * All the elements of pushed are unique.
     * popped.length == pushed.length
     * popped is a permutation of pushed.
     *
     * @param pushed An an array which represents the order in which the values are pushed
     * @param popped An array which represents the order in which values are popped
     * @return whether it is possible to to perform all of the pushes and pops.
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        return validateStackO1(pushed,popped);
    }

    public boolean validateStackON(int[] pushed, int[] popped) {
        //in this approach we attempt to add to the stack
        //we drain the stack whenever possible
        int j = 0;

        Stack<Integer> stack = new Stack<>();

        //always insert into the stack
        for(int val : pushed) {
            stack.push(val);

            //if the stack is not empty
            //attempt to drain the stack
            while(!stack.isEmpty() && popped[j] == stack.peek()) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public boolean validateStackO1(int[] pushed, int[] popped) {
        //in this approach we pretend that the pushed array
        //is a stack and perform the operation
        int j = 0;
        int stackPointer = -1;

        for(int val : pushed) {
            stackPointer++;
            pushed[stackPointer] = val;
            while (stackPointer >= 0 && popped[j] == pushed[stackPointer]) {
                stackPointer--;
                j++;
            }
        }

        return stackPointer == -1;
    }
}
