import java.io.*;
import java.util.*;

class Edge {
    int from, to, weight;

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static Set<Integer> negativeCycleNodes = new HashSet<>();
    private static final long NEGATIVE_INF = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] cityEarn = new int[n];
        List<Edge> graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.add(new Edge(s, e, c));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cityEarn[i] = Integer.parseInt(st.nextToken());
        }

        long[] dist = new long[n];
        boolean hasNegativeCycle = bellmanFord(dist, graph, cityEarn, start, n);

        if (dist[end] == NEGATIVE_INF) {
            System.out.println("gg");
            return;
        }
        if (hasNegativeCycle && hasInfiniteProfit(start, graph, n, end)) {
            System.out.println("Gee");
            return;
        }
        System.out.println(dist[end]);
    }

    private static boolean bellmanFord(long[] dist, List<Edge> graph, int[] cityEarn, int start, int n) {
        Arrays.fill(dist, NEGATIVE_INF);
        dist[start] = cityEarn[start];
        boolean hasNegativeCycle = false;

        for (int i = 0; i < n; i++) {
            for (Edge e : graph) {
                int node = e.from;
                int next = e.to;
                int weight = e.weight;
                if (dist[node] != NEGATIVE_INF && dist[node] + cityEarn[next] - weight > dist[next]) {
                    dist[next] = dist[node] + cityEarn[next] - weight;

                    if (i == n - 1) {
                        hasNegativeCycle = true;
                        negativeCycleNodes.add(next);
                    }
                }
            }
        }
        return hasNegativeCycle;
    }

    private static boolean hasInfiniteProfit(int start, List<Edge> graph, int n, int end) {
        boolean[] reachableToEnd = getReachableToEnd(graph, n, end);

        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (negativeCycleNodes.contains(cur) && reachableToEnd[cur]) {
                return true;
            }
            for (Edge e : graph) {
                if (e.from == cur && !visited[e.to]) {
                    visited[e.to] = true;
                    q.offer(e.to);
                }
            }
        }
        return false;
    }

    private static boolean[] getReachableToEnd(List<Edge> graph, int n, int end) {
        List<List<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        for (Edge e : graph) {
            reverseGraph.get(e.to).add(e.from);
        }

        boolean[] reachable = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(end);
        reachable[end] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int prev : reverseGraph.get(cur)) {
                if (!reachable[prev]) {
                    reachable[prev] = true;
                    q.offer(prev);
                }
            }
        }
        return reachable;
    }
}