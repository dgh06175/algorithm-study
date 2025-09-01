import java.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int e;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(a, b, c));
            graph.get(b).add(new Edge(b, a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int oneToV1 = dijkstra(graph, 1, v1);
        int v1ToV2 = dijkstra(graph, v1, v2);
        int v2ToN = dijkstra(graph, v2, n);

        int oneToV2 = dijkstra(graph, 1, v2);
        int v1ToN = dijkstra(graph, v1, n);

        if (v1ToV2 == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        long path1 = (long) oneToV1 + (long) v2ToN + (long) v1ToV2;
        long path2 = (long) oneToV2 + (long) v1ToN + (long) v1ToV2;

        long ans = Math.min(path1, path2);
        if (ans >= Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);

        // 1 -> v1
        // v1 -> v2
        // v2 -> N
        // vs
        // 1 -> v2
        // v2 -> v1
        // v1 -> N
    }

    static int dijkstra(ArrayList<ArrayList<Edge>> graph, int start, int end) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<State> queue = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));

        dist[start] = 0;
        queue.offer(new State(start, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();

            if (cur.cost > dist[cur.node]) {
                continue;
            }

            for (Edge next : graph.get(cur.node)) {
                int nextDist;
                if (dist[cur.node] == Integer.MAX_VALUE) {
                    nextDist = Integer.MAX_VALUE;
                } else {
                    nextDist = dist[cur.node] + next.weight;
                }

                if (dist[next.end] > nextDist) {
                    dist[next.end] = nextDist;
                    queue.offer(new State(next.end, nextDist));
                }
            }
        }

        return dist[end];
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
    int start;
    int end;
    int weight;

    Edge(int s, int e, int weight) {
        this.start = s;
        this.end = e;
        this.weight = weight;
    }
}