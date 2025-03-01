import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class State {
        int node, totalWeight;

        State(int node, int totalWeight) {
            this.node = node;
            this.totalWeight = totalWeight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n + 1];
        List<Edge>[] reverse_graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverse_graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, time));
            reverse_graph[to].add(new Edge(from, time));
        }

        int[] dist = dijkstra(n, x, graph);
        int[] reverse_dist = dijkstra(n, x, reverse_graph);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            max = Math.max(max, dist[i] + reverse_dist[i]);
        }
        System.out.println(max);
    }

    private static int[] dijkstra(int n, int start, List<Edge>[] graph) {
        // Queue<State> pq = new PriorityQueue<>((s1, s2) ->
        // Integer.compare(s1.totalWeight, s2.totalWeight));
        Queue<Integer> pq = new PriorityQueue<>();
        // pq.offer(new State(start, 0));
        pq.offer(start);
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE - 1);
        d[start] = 0;

        while (!pq.isEmpty()) {
            // State state = pq.poll();
            int node = pq.poll();
            // int node = state.node;

            for (Edge next : graph[node]) {
                int nextNode = next.to;
                int newWeight = d[node] + next.weight;
                if (d[nextNode] > newWeight) {
                    d[nextNode] = newWeight;
                    pq.offer(nextNode);
                }
            }
        }

        return d;
    }
}
