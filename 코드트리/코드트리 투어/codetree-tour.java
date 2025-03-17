import java.util.*;
import java.io.*;

public class Main {
    static UserSolution userSolution = new UserSolution();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 100) {
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                int[] vAry = new int[m];
                int[] uAry = new int[m];
                int[] wAry = new int[m];

                for (int i = 0; i < m; i++) {
                    int v = Integer.parseInt(st.nextToken());
                    vAry[i] = v;
                    int u = Integer.parseInt(st.nextToken());
                    uAry[i] = u;
                    int w = Integer.parseInt(st.nextToken());
                    wAry[i] = w;
                }
                userSolution.init(n, m, vAry, uAry, wAry);
            } else if (type == 200) {
                int id = Integer.parseInt(st.nextToken());
                int revenue = Integer.parseInt(st.nextToken());
                int dest = Integer.parseInt(st.nextToken());
                userSolution.createPromotion(id, revenue, dest);
            } else if (type == 300) {
                int id = Integer.parseInt(st.nextToken());
                userSolution.removePromotion(id);
            } else if (type == 400) {
                int saleId = userSolution.salePromotion();
                System.out.println(saleId);
            } else if (type == 500) {
                int s = Integer.parseInt(st.nextToken());
                userSolution.changeStart(s);
            }
        }
    }
}

class Node {
    int from, to, weight;

    Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Promotion {
    int id, revenue, dest;

    Promotion(int id, int revenue, int dest) {
        this.id = id;
        this.revenue = revenue;
        this.dest = dest;
    }
}

class UserSolution {
    List<List<Node>> graph;
    Queue<Promotion> promotions;
    Set<Integer> existIds;
    int start;
    int[] currentDist;

    void init(int n, int m, int[] v, int[] u, int[] w) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        existIds = new HashSet<>();

        for (int i = 0; i < m; i++) {
            if (v[i] == u[i])
                continue;
            List<Node> nodes = graph.get(v[i]);
            List<Node> nodes2 = graph.get(u[i]);
            Node newNode = new Node(v[i], u[i], w[i]);
            Node newNode2 = new Node(u[i], v[i], w[i]);

            nodes.removeIf(node -> node.from == newNode.from && node.to == newNode.to && node.weight >= newNode.weight);
            boolean hasAlready = nodes.stream().anyMatch(
                    node -> node.from == newNode.from && node.to == newNode.to && node.weight < newNode.weight);
            if (!hasAlready) {
                nodes.add(newNode);
            }

            nodes2.removeIf(
                    node -> node.from == newNode2.from && node.to == newNode2.to && node.weight >= newNode2.weight);
            boolean hasAlready2 = nodes2.stream().anyMatch(
                    node -> node.from == newNode2.from && node.to == newNode2.to && node.weight < newNode2.weight);
            if (!hasAlready2) {
                nodes2.add(newNode2);
            }
        }

        start = 0;
        currentDist = dijkstra(start);
        promotions = new PriorityQueue<>((p1, p2) -> {
            int profit1 = p1.revenue - currentDist[p1.dest];
            int profit2 = p2.revenue - currentDist[p2.dest];
            if (profit1 == profit2) {
                return Integer.compare(p1.id, p2.id);
            }
            return Integer.compare(profit2, profit1);
        });
    }

    void createPromotion(int id, int revenue, int dest) {
        Promotion promotion = new Promotion(id, revenue, dest);
        existIds.add(id);
        promotions.offer(promotion);
    }

    void removePromotion(int id) {
        existIds.remove(id);
    }

    int salePromotion() {
        while (!promotions.isEmpty()) {
            Promotion promotion = promotions.peek();
            if (!existIds.contains(promotion.id)) {
                promotions.poll();
                continue;
            }
            int profit = promotion.revenue - currentDist[promotion.dest];
            if (profit >= 0) {
                promotions.poll();
                existIds.remove(promotion.id);
                return promotion.id;
            } else {
                break;
            }
        }
        return -1;
    }

    void changeStart(int s) {
        start = s;
        currentDist = dijkstra(s);
        Queue<Promotion> newPromotions = new PriorityQueue<>((p1, p2) -> {
            int profit1 = p1.revenue - currentDist[p1.dest];
            int profit2 = p2.revenue - currentDist[p2.dest];
            if (profit1 == profit2) {
                return Integer.compare(p1.id, p2.id);
            }
            return Integer.compare(profit2, profit1);
        });
        newPromotions.addAll(promotions);
        promotions = newPromotions;
    }

    private int[] dijkstra(int s) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE - 1000);
        dist[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[] { s, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if (curCost > dist[curNode])
                continue;

            for (Node next : graph.get(curNode)) {
                int nextCost = curCost + next.weight;
                if (nextCost < dist[next.to]) {
                    dist[next.to] = nextCost;
                    pq.offer(new int[] { next.to, nextCost });
                }
            }
        }

        return dist;
    }
}
