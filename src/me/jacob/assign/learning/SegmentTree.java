package me.jacob.assign.learning;

import java.util.Arrays;

public class SegmentTree {

    public static void main(String[] args) {
        int[] arr = generate(1000);
        System.out.println(Arrays.toString(arr));
        SegmentTree tree = new SegmentTree(arr);
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

    private final Node root;

    public SegmentTree(int[] arr) {
        this.root = construct(arr, 0, arr.length - 1);
    }

    public int query(int left, int right) {
        return query(root, left, right);
    }

    private int query(Node root, int left, int right) {
        //1. check if there is a total overlap
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.minRange >= left && root.maxRange <= right) {
            return root.val;
            //2. Check if there is no overlap
        } else if (left > root.maxRange || right < root.minRange) {
            return Integer.MAX_VALUE;
        } else {
            return Math.min(query(root.left, left, right), query(root.right, left, right));
        }
    }

    public void update(int index, int newVal) {
        update(this.root, index, newVal);
    }

    private void update(Node root, int index, int newVal) {
        //1. We found the leaf node. Update
        if (root.minRange == root.maxRange && root.minRange == index) {
            root.val = newVal;
            //2. the index overlaps completely
        } else if (index >= root.minRange && index <= root.maxRange) {
            //update the left and right subtrees
            update(root.left, index, newVal);
            update(root.right, index, newVal);

            //recalculate the new value
            root.val = Math.min(root.left.val, root.right.val);
        }

        //3. There is no overlap return
    }

    private Node construct(int[] arr, int l, int r) {
        if (l == r) {
            return new Node(l, r, arr[l]);
        }

        int middle = (l + r) / 2;
        Node node = new Node(l, r, 0);
        node.left = construct(arr, l, middle);
        node.right = construct(arr, middle + 1, r);
        node.val = Math.min(node.left.val, node.right.val);

        return node;
    }

    private static class Node {
        private int minRange;
        private int maxRange;
        private int val;
        private Node left;
        private Node right;

        public Node(int minRange, int maxRange, int val) {
            this.minRange = minRange;
            this.maxRange = maxRange;
            this.val = val;
        }
    }
}
