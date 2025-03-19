import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int to, weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        System.out.println(MST(graph, v));
    }

    private static long MST(List<List<Edge>> graph, int v) {
        long ans = 0L;
        Queue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.weight, e2.weight));
        Set<Integer> tree = new HashSet<>();

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (tree.contains(cur.to))
                continue;
            tree.add(cur.to);
            ans += cur.weight;
            if (tree.size() == v)
                break;
            for (Edge next : graph.get(cur.to)) {
                if (!tree.contains(next.to)) {
                    pq.offer(next);
                }
            }
        }
        return ans;
    }
}