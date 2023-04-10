package me.jacob.assign.algorithms.datastructures;

/**
 * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
 * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement the MyLinkedList class:
 *
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
 */
public class MyLinkedList {

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        for(int i=0;i<5;i++) {
            linkedList.addAtTail(i);
        }

        for(int i=0;i<5;i++) {
            System.out.println(linkedList.get(i));
        }

        System.out.println("-------");

        for(int i=0;i<5;i++) {
            System.out.println("------------");
            System.out.println("Deleted "+i);
            linkedList.deleteAtIndex(i);
            for(int j=0;j<5;j++) {
                System.out.println(linkedList.get(j));
            }
            System.out.println("---------");
        }
    }

    private Node sentinel;

    public MyLinkedList() {
        this.sentinel = new Node(-12345);
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
    }

    public int get(int index) {
        Node get = getIndex(index);
        if(get==null) {
            return -1;
        } else {
            return get.val;
        }
    }

    private Node getIndex(int index) {
        if(index<0)
            return null;

        Node root = sentinel.next;
        int count = 0;
        while(root!=sentinel) {
            if(count==index)
                return root;

            root=root.next;
            count++;
        }

        return null;
    }

    public void addAtHead(int val) {
        link(sentinel,val);
    }

    public void addAtTail(int val) {
        link(sentinel.prev,val);
    }

    private void link(Node node, int val) {
        Node insert = new Node(val);
        Node next = node.next;

        node.next = insert;
        next.prev = insert;

        insert.next = next;
        insert.prev = node;
    }

    public void addAtIndex(int index, int val) {
        Node get;
        if(index == 0) {
            get = sentinel;
        } else {
            get = getIndex(index - 1);
        }

        if(get!=null) {
            link(get,val);
        }
    }

    public void deleteAtIndex(int index) {
        Node get = getIndex(index);
        if(get!=null) {
            unlink(get);
        }
    }

    private void unlink(Node node) {
        Node next = node.next;
        Node prev = node.prev;

        prev.next = next;
        next.prev = prev;
    }

    private static class Node {
        private int val;
        private Node next;
        private Node prev;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

}
