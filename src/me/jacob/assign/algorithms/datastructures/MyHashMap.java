package me.jacob.assign.algorithms.datastructures;

/**
 * 706. Design HashMap
 *
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the MyHashMap class:
 *
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 */
public class MyHashMap {

    private Node[] table;

    public static void main(String[] args) {
        MyHashMap map = new MyHashMap();
        map.put(769,729);
        map.remove(769);
        map.put(379,724);
        map.remove(415);

    }

    private final int buckets = 1;

    public MyHashMap() {
        table = new Node[buckets];
    }

    public void put(int key, int value) {
        int bucket = hash(key);
        if(table[bucket]==null) {
            table[bucket] = new Node(key,value);
        } else {
            table[bucket].putPair(key,value);
        }
    }

    public int get(int key) {
        int bucket = hash(key);
        if(table[bucket]==null)
            return -1;

        return table[bucket].get(key);
    }

    public void remove(int key) {
        int bucket = hash(key);
        if(table[bucket]!=null) {
            if(table[bucket].getKey() == key) {
                table[bucket] = table[bucket].getNext();
            } else {
                table[bucket].remove(key);
            }
        }
    }

    private int hash(int key) {
        return key % buckets;
    }

    private class Node {
        private int key;
        private int value;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public void putPair(int key, int value) {
            Node root = this;
            Node prev = this;
            while(root!=null) {
                if(root.key == key) {
                    root.value = value;
                    return;
                }

                prev = root;
                root = root.next;
            }

            prev.next = new Node(key,value);
        }

        public int get(int key) {
            Node root = this;
            while(root!=null) {
                if(root.key == key)
                    return root.value;

                root = root.next;
            }

            return -1;
        }

        public Node getNext() {
            return next;
        }

        public int getKey() {
            return key;
        }

        public void remove(int key) {
            Node prev = this;
            Node root = this;
            while(root!=null) {
                if(root.key == key) {
                    prev.next = root.next;
                    return;
                }
                prev = root;
                root = root.next;
            }
        }
    }
}