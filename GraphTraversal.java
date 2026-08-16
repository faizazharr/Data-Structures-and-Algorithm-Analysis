import java.util.*;

public class GraphTraversal {
    Map<Integer, List<Integer>> graph = new HashMap<>();

    void addEdge(int u, int v) {
        graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    void dfs(int start) {
        System.out.print("DFS dari " + start + ": ");
        Set<Integer> visited = new HashSet<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    void dfsHelper(int node, Set<Integer> visited) {
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) dfsHelper(neighbor, visited);
        }
    }

    void bfs(int start) {
        System.out.print("BFS dari " + start + ": ");
        Set<Integer> visited = new HashSet<>();
        java.util.Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GraphTraversal g = new GraphTraversal();
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 5);

        g.dfs(1);
        g.bfs(1);
    }
}
