package me.jacob.assign.algorithms.graph;

import me.jacob.assign.TreeNode;

public class Problem1339 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2= new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5= new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;

        node3.left = node6;

        node2.left = node4;
        node2.right = node5;

        Problem1339 p = new Problem1339();

        System.out.println(p.maxProduct(node1));
    }

    private long maxProduct = 0;
    private final static long modulo = 1000000007;

    /**
     * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge such that the product of the sums of the subtrees is maximized.
     * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
     * Note that you need to maximize the answer before taking the mod and not after taking it.
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 5 * 104].
     * 1 <= Node.val <= 104
     */
    public int maxProduct(TreeNode root) {
        long totalSum = getTotalSum(root);
        getMaxProduct(root,totalSum);
        return (int) (maxProduct % modulo);
    }

    /**
     * This uses a brute force approach to calculate the total product of every subtree
     * and work out which has the maximum sum. It does it by calculating the sum of the current
     * subtree, and multiplying it by the totalSum - subtree i.e. the mutually exclusive other subtree.
     *
     * We avoid recalculating the sum of subtrees by simply having this method return the current sum
     */
    private long getMaxProduct(TreeNode root, long totalSum) {
        //sum of null root is 0
        if(root == null)
            return 0;

        long sum = root.val;

        //get the sum of the left and right subtrees
        sum += getMaxProduct(root.left,totalSum);
        sum += getMaxProduct(root.right,totalSum);

        //calculate the product of the the two subtrees
        long product = sum * (totalSum - sum);

        //take the bigger product.
        if(product > maxProduct)
            maxProduct = product;

        return sum;
    }

    /**
     * Use a simple preorder DFS to calculate the total sum
     * of the binary tree
     */
    private long getTotalSum(TreeNode root) {
        if(root==null)
            return 0;

        long sum = root.val;

        sum += getTotalSum(root.left);
        sum += getTotalSum(root.right);

        return sum;
    }
}
