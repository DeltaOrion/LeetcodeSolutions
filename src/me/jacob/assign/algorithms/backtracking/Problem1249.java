package me.jacob.assign.algorithms.backtracking;

import java.util.Stack;

public class Problem1249 {

    /**
     * Given a string s of '(' , ')' and lowercase English characters.
     *
     * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
     *
     * Formally, a parentheses string is valid if and only if:
     *
     * It is the empty string, contains only lowercase characters, or
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     */

    public String minRemoveToMakeValid(String s) {
        Stack<Character> word = new Stack<>();
        int open = 0;
        int closed = 0;
        for(int i=s.length()-1;i>=0;i--) {
            if(s.charAt(i)==')') {
                closed++;
                word.push(')');
            } else if(s.charAt(i)=='(') {
                if(open<closed) {
                    open++;
                    word.push('(');
                }
            } else {
                word.push(s.charAt(i));
            }
        }

        closed = 0;
        open = 0;

        StringBuilder result = new StringBuilder();
        while(!word.isEmpty()) {
            char c = word.pop();
            if(c == '(') {
                open++;
                result.append('(');
            } else if(c == ')') {
                if(closed < open) {
                    closed++;
                    result.append(')');
                }
            } else {
                result.append(c);
            }
        }

        //maybe if we can add in
        return result.toString();
    }
}
