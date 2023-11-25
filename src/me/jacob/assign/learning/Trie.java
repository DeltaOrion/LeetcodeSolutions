package me.jacob.assign.learning;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cattle");
        trie.insert("cat");
        trie.insert("joke");
        trie.insert("kin");
        trie.insert("kit");

        System.out.println(trie.autocomplete(""));
    }

    private TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String str) {
        TrieNode root = this.root;
        for (int i = 0; i < str.length(); i++) {
            TrieNode child = root.children[str.charAt(i) - 'a'];
            if (child == null) {
                child = new TrieNode();
                root.children[str.charAt(i) - 'a'] = child;
                root.size++;
            }

            if (i == str.length() - 1) {
                child.terminal = true;
            }
            root = child;
        }
    }

    public boolean containsString(String str) {
        TrieNode root = find(str);
        return root != null && root.terminal;
    }

    private TrieNode find(String str) {
        TrieNode root = this.root;
        for (int i = 0; i < str.length(); i++) {
            root = root.children[str.charAt(i) - 'a'];
            if (root == null) {
                return null;
            }
        }

        return root;
    }

    private List<String> autocomplete(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode node = find(prefix);
        if (node == null) {
            return result;
        }

        autocomplete(node, result, new StringBuilder(prefix));
        return result;
    }

    private void autocomplete(TrieNode root, List<String> result, StringBuilder current) {
        if (root.terminal) {
            result.add(current.toString());
        }

        for (int i = 0; i < root.children.length; i++) {
            if (root.children[i] != null) {
                current.append((char) (i + 'a'));
                autocomplete(root.children[i], result, current);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }

    private void delete(String str) {
        delete(str, root, 0);
    }

    private boolean delete(String str, TrieNode node, int i) {
        if (node == null) {
            return false;
        }

        if (i == str.length()) {
            if (!node.terminal) {
                return false;
            }

            node.terminal = false;
            return node.size == 0;
        } else {
            if (delete(str, node.children[str.charAt(i) - 'a'], i + 1)) {
                node.children[str.charAt(i) - 'a'] = null;
                node.size--;
                return node.size == 0 && !node.terminal;
            }

            return false;
        }
    }

    private static class TrieNode {
        //couple of ways you can setup a trie node. You can have an array of children and each index represents
        //the character, which is what we do here. Otherwise you can use a hashmap.

        private final int NUMBER_OF_CHARACTERS = 26;
        private TrieNode[] children;
        private int size = 0;
        boolean terminal;

        public TrieNode() {
            children = new TrieNode[NUMBER_OF_CHARACTERS];
            this.terminal = false;
        }
    }
}
