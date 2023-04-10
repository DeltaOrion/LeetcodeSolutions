package me.jacob.assign.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.
 *
 * Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.
 *
 * Notice that you can return the vertices in any order.
 *
 *
 */
class Problem1557 {

    public static void main(String[] args) {
        Problem1557 vo = new Problem1557();
        List<List<Integer>> in = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(0);
        a.add(1);
        in.add(a);
        List<Integer> b = new ArrayList<>();
        b.add(0);
        b.add(2);
        in.add(b);
        List<Integer> c = new ArrayList<>();
        c.add(2);
        c.add(5);
        in.add(c);
        List<Integer> d = new ArrayList<>();
        d.add(3);
        d.add(4);
        in.add(d);
        List<Integer> e = new ArrayList<>();
        e.add(4);
        e.add(2);
        in.add(e);
        vo.findSmallestSetOfVertices(6,in);
    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] connections = new boolean[n];
        //counting out degree
        for(List<Integer> edge : edges) {
            connections[edge.get(1)] = true;
        }

        List<Integer> sol = new ArrayList<>();
        for(int i=0;i<connections.length;i++) {
            if(!connections[i])
                sol.add(i);
        }

        return sol;
    }
}
