package me.jacob.assign.algorithms.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Problem847 {

    public static void main(String[] args) {
        Problem847 p = new Problem847();
        System.out.println(p.shortestPathLength(new int[][]{{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}}));
    }

    public int shortestPathLength(int[][] graph) {
        //DP [i][j] stores whether with the current bitmask we have visited the node with the current state
        //if we have that is a really bad thing because it means we have done a cycle without changing the state
        //of the search
        boolean[][] DP = new boolean[graph.length][1 << graph.length];
        Queue<Node> bfs = new ArrayDeque<>();

        int minCount = Integer.MAX_VALUE;
        Node minNode = null;

        //add all of the initial starting points
        for (int i = 0; i < graph.length; i++) {
            bfs.offer(new Node(i, 0, (1 << i), null));
        }

        while (!bfs.isEmpty()) {
            Node node = bfs.poll();
            if (DP[node.graphNode][node.visited] || node.pathLength > minCount) {
                continue;
            }

            //all of the nodes have been visited
            if (node.visited == (1 << graph.length) - 1) {
                minCount = node.pathLength;
                minNode = node;
                continue;
            }

            for (int neighbour : graph[node.graphNode]) {
                bfs.add(new Node(neighbour, node.pathLength + 1, node.visited | (1 << neighbour), node));
            }

            //mark this so that if we ever arrive at this state again flag it.
            DP[node.graphNode][node.visited] = true;
        }

        Stack<Integer> stack = new Stack<>();
        while (minNode.previous != null) {
            stack.push(minNode.graphNode);
            minNode = minNode.previous;
        }

        stack.push(minNode.graphNode);
        System.out.println(stack);

        return minCount;
    }

    private static class Node {
        private final int graphNode;
        private final int pathLength;
        private final int visited;
        private Node previous;

        public Node(int graphNode, int pathLength, int visited, Node previous) {
            this.graphNode = graphNode;
            this.pathLength = pathLength;
            this.visited = visited;
            this.previous = previous;
        }
    }
}
