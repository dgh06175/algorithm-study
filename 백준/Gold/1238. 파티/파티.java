import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }

        int[] minDistances = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            minDistances[i] += calcMinDistance(graph, x, n, i);
        }

        for (int i = 1; i < n + 1; i++) {
            minDistances[i] += calcMinDistance(graph, i, n, x);
        }

        int max = Arrays.stream(minDistances).max().getAsInt();
        System.out.println(max);
    }

    static int calcMinDistance(List<Edge>[] graph, int start, int n, int x) {
        int[] D = new int[n + 1];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1.dist));
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int node = currentNode.node;
            int dist = currentNode.dist;
            if (dist > D[node]) {
                continue;
            }

            for (Edge edge : graph[node]) {
                int newDist = dist + edge.weight;
                if (newDist < D[edge.to]) {
                    D[edge.to] = newDist;
                    queue.offer(new Node(edge.to, newDist));
                }
            }
        }

        return D[x];
    }

    static class Node {
        int node;
        int dist;

        Node(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
