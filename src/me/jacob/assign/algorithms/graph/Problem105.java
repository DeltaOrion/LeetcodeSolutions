package me.jacob.assign.algorithms.graph;

import me.jacob.assign.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem105 {

    /**
     * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is
     * the inorder traversal of the same tree, construct and return the binary tree.
     */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode dummy = new TreeNode(0);
        Map<Integer,TreeNode> used = new HashMap<>();
        TreeNode curr = dummy;
        int j =0;
        int i =0;
        while(i<preorder.length && j < preorder.length) {
            //start creating a left sequence
            TreeNode leftSeq = new TreeNode(preorder[i]);
            TreeNode rootSeq = leftSeq;
            while (i < preorder.length-1 && preorder[i] != inorder[j]) {
                i++;
                rootSeq.left = new TreeNode(preorder[i]);
                used.put(rootSeq.val,rootSeq);
                rootSeq = rootSeq.left;
            }

            used.put(rootSeq.val,rootSeq);

            //we have finished creating a left sequence
            //skip all the inorder backtracks
            curr.right = leftSeq;
            while (j<inorder.length && used.get(inorder[j])!=null) {
                curr = used.get(inorder[j]);
                j++;
            }

            i++;

        }

        return dummy.right;
    }

    /*

    private int preOrderIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer,Integer> inorderMap = new HashMap<>();
        for(int i=0;i<inorder.length;i++) {
            inorderMap.put(inorder[i],i);
        }

        return arrayToTree(inorderMap,preorder,0,preorder.length-1);
    }

    public TreeNode arrayToTree(Map<Integer,Integer> inOrder, int[] preorder, int left, int right) {
        if(left > right)
            return null;

        TreeNode root = new TreeNode(preorder[preOrderIndex++]);
        int middle = inOrder.get(root.val);
        root.left = arrayToTree(inOrder,preorder,left,middle-1);
        root.right = arrayToTree(inOrder,preorder,middle+1,right);

        return root;
    }
    */
}
