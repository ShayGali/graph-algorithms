/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
    // Function to form edge between
    // two vertices src and dest
    static void add_edge(ArrayList<ArrayList<Integer>> adj, int src, int dest){
        adj.get(src).add(dest);
        adj.get(dest).add(src);
    }

    // Function which finds all the paths
    // and stores it in paths array
    static void find_paths(ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path,
                           ArrayList<ArrayList<Integer>> parent, int n, int u) {
        // Base Case
        if (u == -1) {
            paths.add(new ArrayList<>(path));
            return;
        }

        // Loop for all the parents
        // of the given vertex
        for (int par : parent.get(u)) {

            // Insert the current
            // vertex in path
            path.add(u);

            // Recursive call for its parent
            find_paths(paths, path, parent, n, par);

            // Remove the current vertex
            path.remove(path.size()-1);
        }
    }

    // Function which performs bfs
    // from the given source vertex
    static void bfs(ArrayList<ArrayList<Integer>> adj, ArrayList<ArrayList<Integer>> parent,
                    int n, int start) {

        // dist will contain shortest distance
        // from start to every other vertex
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer> q = new LinkedList<>();

        // Insert source vertex in queue and make
        // its parent -1 and distance 0
        q.offer(start);

        parent.get(start).clear();
        parent.get(start).add(-1);

        dist[start] = 0;

        // Until Queue is empty
        while (!q.isEmpty()) {
            int u = q.poll();

            for (int v : adj.get(u)) {
                if (dist[v] > dist[u] + 1) {

                    // A shorter distance is found
                    // So erase all the previous parents
                    // and insert new parent u in parent[v]
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                    parent.get(v).clear();
                    parent.get(v).add(u);
                }
                else if (dist[v] == dist[u] + 1) {

                    // Another candidate parent for
                    // shortes path found
                    parent.get(v).add(u);
                }
            }
        }
    }

    // Function which prints all the paths
    // from start to end
    static void print_paths(ArrayList<ArrayList<Integer>> adj, int n, int start, int end){
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<ArrayList<Integer>> parent = new ArrayList<>();

        for(int i = 0; i < n; i++){
            parent.add(new ArrayList<>());
        }

        // Function call to bfs
        bfs(adj, parent, n, start);

        // Function call to find_paths
        find_paths(paths, path, parent, n, end);

        for (ArrayList<Integer> v : paths) {

            // Since paths contain each
            // path in reverse order,
            // so reverse it
            Collections.reverse(v);

            // Print node for the current path
            for (int u : v)
                System.out.print(u + " ");

            System.out.println();
        }
    }

    public static void main (String[] args)
    {

        // Number of vertices
        int n = 6;

        // array of vectors is used
        // to store the graph
        // in the form of an adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }

        // Given Graph
        add_edge(adj, 0, 1);
        add_edge(adj, 0, 2);
        add_edge(adj, 1, 3);
        add_edge(adj, 1, 4);
        add_edge(adj, 2, 3);
        add_edge(adj, 3, 5);
        add_edge(adj, 4, 5);

        // Given source and destination
        int src = 0;
        int dest = n - 1;

        // Function Call
        print_paths(adj, n, src, dest);

    }
}

// This code is contributed by ayush123ngp.
