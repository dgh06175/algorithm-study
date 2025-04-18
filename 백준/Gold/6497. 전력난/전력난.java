import java.io.*;
import java.util.*;

class Edge {
    int to, weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            List<List<Edge>> graph = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                return;
            }
            for (int i = 0; i < m; i++) {
                graph.add(new ArrayList<>());
            }

            int totalCost = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                graph.get(x).add(new Edge(y, z));
                graph.get(y).add(new Edge(x, z));
                totalCost += z;
            }

            int minCost = calcMinCost(graph, m);
            System.out.println(totalCost - minCost);
        }
    }

    private static int calcMinCost(List<List<Edge>> graph, int m) {
        boolean[] visited = new boolean[m];
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight, e2.weight));
        int costSum = 0;

        int START = 0;
        visited[START] = true;
        for (var i : graph.get(START)) {
            pq.add(i);
        }

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;
            visited[cur.to] = true;
            costSum += cur.weight;

            for (var next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
        return costSum;
    }
}