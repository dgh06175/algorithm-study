import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] itemCount = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            itemCount[i] = Integer.parseInt(st.nextToken());
        }

        List<Node>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            int[] dist = dijkstra(graph, i);
            int maxItemCount = 0;
            for (int j = 1; j <= n; j++) {
                if (dist[j] <= m) {
                    maxItemCount += itemCount[j];
                }
            }
            ans = Math.max(ans, maxItemCount);
        }
        System.out.println(ans);
    }

    static int[] dijkstra(List<Node>[] graph, int start) {
        int[] d = new int[n + 1];
        Arrays.fill(d, INF);
        d[start] = 0;

        Queue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.dist, n2.dist));
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int next = cur.to;
            int cost = cur.dist;

            if (d[next] < cost) {
                continue;
            }

            for (Node nextNode : graph[next]) {
                int nextCost = nextNode.dist + cost;
                if (d[nextNode.to] > nextCost) {
                    d[nextNode.to] = nextCost;
                    pq.offer(new Node(nextNode.to, nextCost));
                }
            }
        }
        return d;
    }
}

class Node {
    int to;
    int dist;

    Node(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}