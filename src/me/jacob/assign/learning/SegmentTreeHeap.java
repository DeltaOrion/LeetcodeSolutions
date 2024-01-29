package me.jacob.assign.learning;

import java.util.Arrays;

public class SegmentTreeHeap {

    private int heap[];
    private int size;

    public static void main(String[] args) {
        int[] arr = generate(1000);
        System.out.println(Arrays.toString(arr));
        SegmentTreeHeap tree = new SegmentTreeHeap(arr);
        arr[283] = -100;
        tree.update(283, -100);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (tree.query(i, j) != find(arr, i, j)) {
                    System.out.println("Range Failed: " + i + "->" + j);
                }
            }
        }
    }

    private static int[] generate(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }

        return arr;
    }

    private static int find(int[] arr, int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int x = i; x <= j; x++) {
            min = Math.min(arr[x], min);
        }

        return min;
    }

    public SegmentTreeHeap(int[] arr) {
        heap = new int[2 * nearestPower(arr.length) - 1];
        this.size = arr.length;
        construct(arr, 0, arr.length - 1, 0);
    }

    private void construct(int[] arr, int left, int right, int index) {
        if (left == right) {
            heap[index] = arr[left];
            return;
        }

        int middle = (left + right) / 2;
        construct(arr, left, middle, leftChild(index));
        construct(arr, middle + 1, right, rightChild(index));
        heap[index] = Math.min(heap[leftChild(index)], heap[rightChild(index)]);
    }

    public int query(int left, int right) {
        return query(left, right, 0, size - 1, 0);
    }

    private int query(int leftTarget, int rightTarget, int leftRange, int rightRange, int index) {
        //1. Total overlap
        int middle = (leftRange + rightRange) / 2;
        if (leftRange >= leftTarget && rightRange <= rightTarget) {
            return heap[index];
            //2. No overlap
        } else if (rightTarget < leftRange || leftTarget > rightRange) {
            return Integer.MAX_VALUE;
        } else {
            //Partial Overlap
            return Math.min(
                    query(leftTarget, rightTarget, leftRange, middle, leftChild(index)),
                    query(leftTarget, rightTarget, middle + 1, rightRange, rightChild(index))
            );
        }
    }

    private void update(int index, int newVal) {
        update(index, newVal, 0, size - 1, 0);
    }

    private void update(int updateIndex, int newVal, int leftRange, int rightRange, int index) {
        if (leftRange == rightRange && leftRange == updateIndex) {
            heap[index] = newVal;
        } else if (updateIndex >= leftRange && updateIndex <= rightRange) {
            //point totally overlaps, update both subtrees
            int middle = (leftRange + rightRange) / 2;
            update(updateIndex, newVal, leftRange, middle, leftChild(index));
            update(updateIndex, newVal, middle + 1, rightRange, rightChild(index));

            heap[index] = Math.min(
                    heap[leftChild(index)],
                    heap[rightChild(index)]
            );
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int nearestPower(int n) {
        if (((n - 1) & n) == 0) {
            return n;
        } else {
            int lmb = 0;
            while (n > 0) {
                n = n >> 1;
                lmb++;
            }

            return 1 << lmb;
        }
    }
}
