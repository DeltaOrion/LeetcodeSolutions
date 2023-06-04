package me.jacob.assign.algorithms.datastructures;

public class Problem547 {

    public static void main(String[] args) {
        Problem547 problem = new Problem547();
        int[][] isConnected = new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        int size = problem.findCircleNum(isConnected);
        System.out.println(size);
    }

    /**
     * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
     * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
     *
     * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
     *
     * Return the total number of provinces.
     */
    public int findCircleNum(int[][] isConnected) {
        //create and populate union find
        int[] unionFind = new int[isConnected.length];
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 0)
                    continue;

                //insert into the union find if it is connected
                union(unionFind, i, j);
            }
        }

        //count the size of the union find
        return size(unionFind);
    }

    private int size(int[] unionFind) {
        int size = 0;
        for (int i = 0; i < unionFind.length; i++) {
            if(unionFind[i] == i)
                size++;
        }

        return size;
    }

    private void union(int[] unionFind, int i, int j) {
        int x = find(unionFind,i);
        int y = find(unionFind,j);

        if(x == y)
            return;

        unionFind[x] = y;
    }

    private int find(int[] unionFind, int i) {
        while(i != unionFind[i])
            i = unionFind[i];

        return i;
    }
}
