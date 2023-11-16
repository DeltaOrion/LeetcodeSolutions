package me.jacob.assign.algorithms.math;

public class Problem1071 {

    public static void main(String[] args) {
        Problem1071 p = new Problem1071();
        p.gcdOfStrings("ABC", "ABCABC");
    }

    public String gcdOfStrings(String str1, String str2) {
        //string 1 should always be the bigger one
        if (str1.length() < str2.length()) {
            return gcdOfStrings(str2, str1);
        }

        if (str2.length() == 0) {
            return str1;
        }

        int i = 0;
        while (i < str1.length()) {
            for (int j = 0; j < str2.length(); j++) {
                //it can't be done if its not a concatanation!
                if (str1.charAt(i) != str2.charAt(j)) {
                    return "";
                }

                i++;
            }

            if (i + str2.length() > str1.length()) {
                break;
            }
        }

        return gcdOfStrings(str2, str1.substring((str1.length() / str2.length()) * str2.length()));
    }
}
