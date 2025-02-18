import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
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

    static int calcMinDistance(int[][] graph, int start, int n, int x) {
        int[] D = new int[n + 1];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.dist, n2.dist));
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int node = currentNode.node;
            int dist = currentNode.dist;
            if (dist > D[node]) {
                continue;
            }

            for (int i = 1; i < n + 1; i++) {
                if (graph[node][i] == Integer.MAX_VALUE) {
                    continue;
                }

                int newDist = dist + graph[node][i];
                if (newDist < D[i]) {
                    D[i] = newDist;
                    queue.offer(new Node(i, newDist));
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
}
