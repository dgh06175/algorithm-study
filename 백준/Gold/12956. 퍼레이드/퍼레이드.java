import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;

    static class Edge {
        int to, w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex, dist;

        public Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        int[][] roads = new int[m][3];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
            roads[i] = new int[] { u, v, w };
        }

        int[][] dist = new int[n][n];
        long[][] ways = new long[n][n];

        for (int i = 0; i < n; i++) {
            dijkstra(i, n, graph, dist[i], ways[i]);
        }

        int[] ans = new int[m];
        for (int r = 0; r < m; r++) {
            int a = roads[r][0];
            int b = roads[r][1];
            int c = roads[r][2];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (dist[i][j] == INF)
                        continue;
                    long total = ways[i][j];
                    long use = 0;
                    if (dist[i][j] == dist[i][a] + c + dist[b][j]) {
                        use += ways[i][a] * ways[b][j];
                    }
                    if (dist[i][j] == dist[i][b] + c + dist[a][j]) {
                        use += ways[i][b] * ways[a][j];
                    }
                    if (use == total) {
                        cnt++;
                    }
                }
            }
            ans[r] = cnt;
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < m; r++) {
            sb.append(ans[r]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    static void dijkstra(int src, int n, ArrayList<Edge>[] graph, int[] dist, long[] ways) {
        Arrays.fill(dist, INF);
        Arrays.fill(ways, 0);
        dist[src] = 0;
        ways[src] = 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(src, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.vertex;
            int d = cur.dist;
            if (d != dist[u])
                continue;
            for (Edge edge : graph[u]) {
                int v = edge.to;
                int nd = d + edge.w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    ways[v] = ways[u];
                    pq.offer(new Node(v, nd));
                } else if (nd == dist[v]) {
                    ways[v] += ways[u];
                }
            }
        }
    }
}
