package me.jacob.assign.algorithms.array;

public class Problem844 {

    /**
     * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
     *
     * Note that after backspacing an empty text, the text will continue empty.
     */
    public boolean backspaceCompare(String s, String t) {
        int i = s.length()-1;
        int j = t.length()-1;

        while (i >= 0 && j >= 0) {
            //collect backspaces for s
            i = collectBackspace(s,i);
            j = collectBackspace(t,j);

            if(i>=0 && j>=0) {
                if(s.charAt(i) != t.charAt(j))
                    return false;
            } else if(i>=0 || j>=0) {
                return false;
            }

            i--;
            j--;
        }

        i=collectBackspace(s,i);
        j=collectBackspace(t,j);

        return i==j;
    }

    private int collectBackspace(String s, int i) {
        int backSpace = 0;
        while(i>=0 && (s.charAt(i)=='#' || backSpace > 0)) {
            if(s.charAt(i)=='#') {
                backSpace++;
            } else {
                backSpace--;
            }
            i = i-1;
        }

        return i;
    }
}
