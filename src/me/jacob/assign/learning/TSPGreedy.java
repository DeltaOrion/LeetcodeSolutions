package me.jacob.assign.learning;

import java.util.*;

public class TSPGreedy {

    public static void main(String[] args) {
        System.out.println(TSPGreedy(new int[][]{{0, 10, 15, 20}, {5, 0, 9, 10}, {6, 13, 0, 12}, {8, 8, 9, 0}}));
    }

    private static int TSPGreedy(int[][] graph) {
        int n = graph.length;
        int[] unionFind = new int[n];
        init(unionFind);

        Map<Integer, Node> hamiltonianPath = new HashMap<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if(i == j)
                    continue;
                queue.add(new Edge(i, j, graph[i][j]));
            }
        }

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            //ensuring adding the edge doesn't form a cycle
            if (cycle(unionFind, edge.i, edge.j))
                continue;

            //ensure adding the edge does not increase the degree to degree three for node i.
            Node iNode = hamiltonianPath.get(edge.i);
            Node jNode = hamiltonianPath.get(edge.j);
            if (jNode == null) {
                jNode = new Node(edge.j);
                hamiltonianPath.put(edge.j, jNode);
            }

            if (iNode == null) {
                iNode = new Node(edge.i);
                hamiltonianPath.put(edge.i, iNode);
            }

            if (iNode.next != null || jNode.prev != null) {
                continue;
            }

            iNode.next = jNode;
            jNode.prev = iNode;
            union(unionFind, edge.i, edge.j);
        }

        Node root = null;
        for (Node node : hamiltonianPath.values()) {
            if (node.prev == null) {
                root = node;
                break;
            }
        }

        List<Integer> path = new ArrayList<>();
        int cost = 0;
        while (root.next != null) {
            path.add(root.node);
            cost += graph[root.node][root.next.node];
            root = root.next;
        }

        path.add(root.node);
        System.out.println(path);
        return cost;
    }

    private static boolean cycle(int[] unionFind, int i, int j) {
        int iSet = findSet(unionFind, i);
        int jSet = findSet(unionFind, j);

        return jSet == iSet;
    }

    private static void union(int[] unionFind, int i, int j) {
        int iSet = findSet(unionFind, i);
        int jSet = findSet(unionFind, j);

        unionFind[iSet] = jSet;
    }

    private static int findSet(int[] unionFind, int i) {
        while (unionFind[i] != i) {
            i = unionFind[i];
        }

        return i;
    }

    private static void init(int[] unionFind) {
        for (int i = 0; i < unionFind.length; i++) {
            unionFind[i] = i;
        }
    }

    private static class Edge {
        private int i;
        private int j;
        private int weight;

        public Edge(int i, int j, int weight) {
            this.i = i;
            this.j = j;
            this.weight = weight;
        }
    }

    private static class Node {
        private int node;
        Node next;
        Node prev;

        public Node(int node) {
            this.node = node;
        }
    }
}
