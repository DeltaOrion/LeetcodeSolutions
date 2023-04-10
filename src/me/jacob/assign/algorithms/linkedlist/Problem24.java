package me.jacob.assign.algorithms.linkedlist;

import me.jacob.assign.ListNode;

public class Problem24 {

    /**
     * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying
     * the values in the list's nodes (i.e., only nodes themselves may be changed.)
     */
    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode(0,head);
        ListNode prev = sentinel;
        while(head != null && head.next!=null) {
            ListNode next = head.next;
            prev.next = next;
            head.next = next.next;
            next.next = head;

            prev = head;
            head = head.next;
        }

        return sentinel.next;
    }
}
