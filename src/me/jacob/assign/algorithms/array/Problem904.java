package me.jacob.assign.algorithms.array;

public class Problem904 {

    public static void main(String[] args) {
        Problem904 p = new Problem904();
        System.out.println(p.totalFruit(new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3}));
    }

    public int totalFruit(int[] fruits) {
        int count = 0;
        int max = 0;
        int leftBasket = -1;
        int rightBasket = -1;
        int firstLeft = 0;
        int firstRight = 0;
        for (int i = 0; i < fruits.length; i++) {
            if (fruits[i] != leftBasket && fruits[i] != rightBasket) {
                if (leftBasket == -1) {
                    leftBasket = fruits[i];
                } else {
                    firstRight = firstLeft;
                    rightBasket = leftBasket;
                    firstLeft = i;
                    leftBasket = fruits[i];
                    count = i - firstRight;
                }
            }

            if (i > 0 && fruits[i] != fruits[i - 1]) {
                if (fruits[i] == leftBasket) {
                    firstLeft = i;
                } else if (fruits[i] == rightBasket) {
                    firstRight = i;
                }

                //swap the baskets
                if (firstRight > firstLeft) {
                    int temp = firstRight;
                    int temp2 = rightBasket;
                    rightBasket = leftBasket;
                    firstRight = firstLeft;
                    firstLeft = temp;
                    leftBasket = temp2;
                }
            }

            count++;
            max = Math.max(count, max);
        }

        return max;
    }
}
