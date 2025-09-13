import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int INF = Integer.MAX_VALUE / 2;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[f].add(new Edge(f, t, c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] path = new int[n + 1];
        int[] dist = dijkstra(graph, start, end, path);
        System.out.println(dist[end]);

        ArrayDeque<Integer> paths = new ArrayDeque<>();
        int cur = end;
        while (path[cur] != 0) {
            paths.addFirst(cur);
            cur = path[cur];
        }
        paths.addFirst(start);
        System.out.println(paths.size());
        for (int i : paths) {
            System.out.print(i + " ");
        }

    }

    static int[] dijkstra(List<Edge>[] graph, int start, int end, int[] path) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        Queue<State> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (dist[cur.node] < cur.cost) {
                continue;
            }

            // cur.cost + 다음꺼까지 가는 길이 vs dist[next.node]
            for (Edge next : graph[cur.node]) {
                int nextCost = cur.cost + next.cost;
                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.offer(new State(next.to, nextCost));
                    path[next.to] = cur.node;
                }
            }
        }
        return dist;
    }
}

class State {
    int node;
    int cost;

    State(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }
}

class Edge {
    int from;
    int to;
    int cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}