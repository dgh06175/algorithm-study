import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int V;
    static int E;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        List<Pair>[] graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Pair(v, w));
        }
        int[] d = dijkstra(graph, k);
        for (int i = 1; i <= V; i++) {
            if (d[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(d[i]);
            }
        }
    }

    static int[] dijkstra(List<Pair>[] graph, int start) {
        int[] d = new int[V + 1];
        Arrays.fill(d, INF);
        d[start] = 0;
        Queue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.y, p2.y));
        pq.offer(new Pair(start, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int node = cur.x;
            int cost = cur.y;

            if (d[node] < cost) {
                continue;
            }

            for (Pair next : graph[node]) {
                int nextCost = d[node] + next.y;
                if (nextCost < d[next.x]) {
                    d[next.x] = nextCost;
                    pq.offer(new Pair(next.x, nextCost));
                }
            }
        }

        return d;
    }
}

class Pair {
    int x;
    int y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}