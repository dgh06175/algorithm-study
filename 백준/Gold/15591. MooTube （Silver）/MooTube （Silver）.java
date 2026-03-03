import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // <= 5000
        int Q = Integer.parseInt(st.nextToken()); // <= 5000

        List<Node>[] graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int p, q, r;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken()); // <= 1,000,000,000
            graph[p].add(new Node(q, r));
            graph[q].add(new Node(p, r));
        }

        int ans[][] = new int[N + 1][N + 1];
        // ans[i][j] = i부터 j까지 갈때 만나는 가장 작은 cost값

        for (int i = 1; i < N + 1; i++) {
            ans[i] = bfs(i, N, graph);
        }

        int k, v;
        for (int i = 0; i < Q; i++) { // Q * (V + E) * logV = 5000 * 5000 * 5000 -> 다익스트라 안됨. 미리 최단거리 계산해놔야함
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken()); // <= 1,000,000,000
            v = Integer.parseInt(st.nextToken());
            int count = 0;
            for (int c : ans[v]) {
                if (c >= k && c < Integer.MAX_VALUE) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int[] bfs(int start, int n, List<Node>[] graph) {
        boolean[] visited = new boolean[n + 1];
        int[] minCost = new int[n + 1];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;
        minCost[start] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node next : graph[now]) {
                if (visited[next.to]) {
                    continue;
                }
                minCost[next.to] = Math.min(minCost[now], next.cost);
                queue.offer(next.to);
                visited[next.to] = true;
            }
        }

        return minCost;
    }
}
