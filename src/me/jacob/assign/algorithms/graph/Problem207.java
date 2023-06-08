package me.jacob.assign.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

public class Problem207 {

    public static void main(String[] args) {

    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //convert into a directed graph
        List<Integer>[] graph = new List[numCourses];
        for(int i=0;i<numCourses;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] prerequisite : prerequisites) {
            graph[prerequisite[0]].add(prerequisite[1]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] stack  = new boolean[numCourses];

        for(int i=0;i<numCourses;i++) {
            //perform DFS
            if(visited[i])
                continue;

            //if we find a cycle we can't finish
            if(hasCycle(i,graph,visited,stack))
                return false;
        }

        //if we traverse every connected component then we can finish
        return true;
    }

    private boolean hasCycle(int i, List<Integer>[] graph, boolean[] visited, boolean[] stack) {
        if(stack[i])
            return true;

        if(visited[i])
            return false;

        stack[i] = true;
        visited[i] = true;
        for(int node : graph[i]) {
            if(hasCycle(node,graph,visited,stack))
                return true;
        }
        stack[i] = false;
        return false;
    }
}

