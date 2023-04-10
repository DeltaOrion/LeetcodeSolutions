package me.jacob.assign.algorithms.linkedlist;

import me.jacob.assign.ListNode;

public class Problem25 {

    /**
     *
     * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
     * You may not alter the values in the list's nodes, only nodes themselves may be changed.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode sentinel = new ListNode(-12345,head);
        ListNode prev = sentinel;
        ListNode fin = head;
        int count = 0;

        while(fin!=null) {
            fin = fin.next;
            count++;

            if(count==k) {
                //we have enough clearance
                ListNode next = head.next;
                head.next = fin;
                ListNode origHead = head;
                for(int i=0;i<k-1;i++) {
                    ListNode n = next.next;
                    next.next = head;

                    head = next;
                    next = n;
                }
                prev.next = head;
                prev = origHead;
                head = origHead.next;
                count = 0;
            }
        }

        return sentinel.next;
    }
}
