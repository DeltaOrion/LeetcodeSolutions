package me.jacob.assign;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        ListNode root = this;
        while (root != null) {
            builder.append(root.val).append(" -> ");
            root = root.next;
        }
        return builder.toString();
    }
}
