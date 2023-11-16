package me.jacob.assign.algorithms.math;

public class Problem952 {

    public static void main(String[] args) {
        Problem952 p = new Problem952();
        System.out.println(p.largestComponentSize(new int[]{4, 6, 15, 35}));
    }

    public int largestComponentSize(int[] nums) {
        int max = 0;
        for(int i=0;i<nums.length;i++) {
            max = Math.max(max, nums[i]);
        }

        //create a unionFind
        int[] unionFind = new int[max + 1];
        int[] rank = new int[max + 1];
        init(unionFind);

        for(int i=0;i<nums.length;i++) {
            int num = nums[i];
            for(int j=2;j*j <= num;j++) {
                if(num % j == 0) {
                    union(unionFind, rank, j, num);
                    union(unionFind, rank, num / j, num);
                }
            }
        }

        max = 0;
        int[] size = new int[unionFind.length];
        for(int i=0;i<nums.length;i++) {
            max = Math.max(max,++size[findSet(unionFind, nums[i])]);
        }

        return max;
    }

    private void init(int[] unionFind) {
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }
    }

    private int findSet(int[] unionFind, int i) {
        while (unionFind[i] != i) {
            i = unionFind[i];
        }

        return i;
    }

    private void union(int[] unionFind, int[] rank, int i, int j) {
        int rootI = findSet(unionFind, i);
        int rootJ = findSet(unionFind, j);

        if (rootI != rootJ) {
            if (rank[rootI] < rank[rootJ]) {
                unionFind[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                unionFind[rootJ] = rootI;
            } else {
                unionFind[rootJ] = rootI;
                rank[rootI]++;
            }
        }
    }
}
