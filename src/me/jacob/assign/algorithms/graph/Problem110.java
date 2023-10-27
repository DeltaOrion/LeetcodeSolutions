package me.jacob.assign.algorithms.graph;

import me.jacob.assign.TreeNode;

public class Problem110 {

    private boolean isBalanced = true;

    public boolean isBalanced(TreeNode root) {
        calculateBalanced(root);
        return isBalanced;
    }

    public int calculateBalanced(TreeNode node) {
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.left != null) {
            leftHeight = calculateBalanced(node.left);
        }

        if (node.right != null) {
            rightHeight = calculateBalanced(node.right);
        }

        //early termination
        if (leftHeight == -1 || rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            isBalanced = false;
            return - -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
