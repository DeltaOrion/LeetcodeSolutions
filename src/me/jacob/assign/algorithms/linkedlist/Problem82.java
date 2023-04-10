package me.jacob.assign.algorithms.linkedlist;

import me.jacob.assign.ListNode;

public class Problem82 {

    /**
     * Given the head of a sorted linked list, delete all nodes that have duplicate numbers,
     * leaving only distinct numbers from the original list. Return the linked list sorted as well.
     */

    public ListNode deleteDuplicates(ListNode head) {
        if(head==null)
            return null;

        ListNode sentinel = new ListNode(0,head);
        ListNode prev = sentinel;

        boolean duplicate = false;
        while(head!=null) {
            if(head.next!=null && head.next.val==head.val) {
                duplicate = true;
            } else {
                if(duplicate) {
                    prev.next = head.next;
                    duplicate = false;
                } else {
                    prev = head;
                }
            }
            head = head.next;
        }

        return sentinel.next;
    }
}
