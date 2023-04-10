package me.jacob.assign.algorithms.graph;

import me.jacob.assign.TreeNode;

import java.util.*;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or
 * memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need
 * to follow this format, so please be creative and come up with different approaches yourself.
 */
public class Problem297 {

    public static void main(String[] args) {
        Problem297 problem297 = new Problem297();
        TreeNode root = new TreeNode(7);
        TreeNode three = new TreeNode(3);
        TreeNode fifteen = new TreeNode(15);
        TreeNode nine = new TreeNode(9);
        TreeNode twenty = new TreeNode(20);
        root.left = three;
        root.right = fifteen;
        fifteen.left = nine;
        fifteen.right = twenty;

        /*
        String serialized = codec.serialize(root);
        System.out.println(serialized);
        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println(deserialized);

        String serialized = codec.serialize(root);
        System.out.println(serialized);
        System.out.println(codec.deserialize(serialized));

         */
        for(int i=0;i<100;i++) {
            System.out.print(i+",null,");
        }
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //1. find height of the tree
        //2. create array of 2^height
        //3. do DFS placing nodes in appropriate spots
        //4. O(4n) = O(n)

        if (root == null)
            return "[]";
        StringBuilder tree = new StringBuilder();
        tree.append('[');
        Queue<SerialNode> bfs = new ArrayDeque<>();
        bfs.add(new SerialNode(root));
        boolean children = true;
        String nll = "null";

        while (!bfs.isEmpty() && children) {
            int length = bfs.size();
            boolean foundChild = false;
            for(int i=0;i<length;i++) {
                SerialNode node = bfs.poll();
                if(node.treeNode==null) {
                    tree.append(nll).append(',');
                } else {
                    tree.append(node.treeNode.val).append(',');
                    bfs.add(new SerialNode(node.treeNode.left));
                    bfs.add(new SerialNode(node.treeNode.right));

                    if(node.treeNode.left!=null || node.treeNode.right!=null)
                        foundChild = true;
                }
            }

            if(!foundChild)
                children = false;
        }

        tree.deleteCharAt(tree.length() - 1);
        tree.append(']');

        return tree.toString();
    }

    private static class SerialNode {

        private TreeNode treeNode;

        public SerialNode(TreeNode treeNode) {
            this.treeNode = treeNode;
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //1. Convert array string to list.
        //2. deserialize
        //3. convert to integer on the way to differentiate between nulls
        if (data.equals("[]"))
            return null;

        List<String> list = toList(data);
        if(list.size()==0)
            return null;

        Queue<TreeNode> bfs = new ArrayDeque<>();
        TreeNode root = new TreeNode(Integer.parseInt(list.get(0)));
        bfs.add(root);

        int i = 1;
        while (i<list.size()) {
            TreeNode node = bfs.poll();
            node.left = getNode(list,i);
            i++;
            node.right = getNode(list,i);
            i++;

            if(node.left!=null)
                bfs.add(node.left);

            if(node.right!=null)
                bfs.add(node.right);
        }

        return root;
    }

    private TreeNode getNode(List<String> list, int i) {
        if(list.get(i).equals("null")) {
            return null;
        } else {
            return new TreeNode(Integer.parseInt(list.get(i)));
        }
    }

    private List<String> toList(String data) {
        List<String> list = new ArrayList<>();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '[' || data.charAt(i) == ' ')
                continue;

            if (data.charAt(i) == ',' || data.charAt(i) == ']') {
                list.add(token.toString());
                token = new StringBuilder();
            } else {
                token.append(data.charAt(i));
            }
        }

        return list;
    }

}

