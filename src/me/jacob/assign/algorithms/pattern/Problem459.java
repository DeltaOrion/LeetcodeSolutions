package me.jacob.assign.algorithms.pattern;

public class Problem459 {

    public static void main(String[] args) {
        Problem459 p = new Problem459();
        System.out.println(p.repeatedSubstringPattern("ababab"));
    }

    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i * i <= s.length(); i++) {
            //if it divides, check if is made up of that division
            if (s.length() % i == 0) {
                //now check it is composed
                int p = i;
                int q = s.length() / p;

                if ((i != 1 && constructed(s, p, q)) || constructed(s, q, p)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean constructed(String s, int p, int q) {
        for (int j = 0; j < p; j++) {
            for (int k = 0; k < q; k++) {
                if (s.charAt(k) != s.charAt(q * j + k)) {
                    return false;
                }
            }
        }

        return true;
    }
}
