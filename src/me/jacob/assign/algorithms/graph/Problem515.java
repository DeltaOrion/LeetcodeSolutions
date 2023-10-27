package me.jacob.assign.algorithms.graph;

import me.jacob.assign.TreeNode;

import java.util.*;

public class Problem515 {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> rows = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int layer = queue.size();
            int max = queue.peek().val;
            for (int i = 0; i < layer; i++) {
                TreeNode node = queue.poll();
                max = Math.max(node.val, max);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }

            rows.add(max);
        }

        return rows;
    }
}
