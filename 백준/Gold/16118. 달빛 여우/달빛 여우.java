import java.io.*;
import java.util.*;

class Node {
    int from, to, weight;

    Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class State {
    int node;
    int totalDist;

    State(int node, int totalDist) {
        this.node = node;
        this.totalDist = totalDist;
    }
}

class WolfState {
    int node;
    int totalDist;
    int isFast;

    WolfState(int node, int totalDist, int isFast) {
        this.node = node;
        this.totalDist = totalDist;
        this.isFast = isFast;
    }
}

public class Main {
    static int n;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(a, b, d));
            graph.get(b).add(new Node(b, a, d));
        }

        // 1번에서 여우 출발하여 모든 노드로 가는 최단 경로 저장
        int[] foxD = foxDijkstra(1);
        // 2번에서 늑대 출발하여 모든 노드로 가는 최단 경로 저장
        int[] wolfD = wolfDijkstra(1);

        // 두 배열 비교 후 여우가 더 짧은 그루터기의 개수 출력
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (foxD[i] < wolfD[i]) {
                count++;
            }
        }
        System.out.println(count);
    }

    // O((E + V) log V) = 104000 * log4000 = 2백만 = 0.02초
    static int[] foxDijkstra(int start) {
        int[] d = new int[n + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.totalDist, s2.totalDist));
        pq.offer(new State(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.totalDist > d[cur.node]) {
                continue;
            }

            for (Node next : graph.get(cur.node)) {
                int nextDist = d[cur.node] + (next.weight * 2);
                if (d[next.to] > nextDist) {
                    d[next.to] = nextDist;
                    pq.offer(new State(next.to, nextDist));
                }
            }
        }
        return d;
    }

    // 처음 시행시 가중치 절반, 다음은 가중치 두배를 반복
    static int[] wolfDijkstra(int start) {
        // 다음번에 빠르게 가는지, 아닌지에 대한 정보에 따라 상태 나누어서 저장, 0은 다음번에 빠르게 아님, 1은 다음번에 빠르게
        int[][] d = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }
        PriorityQueue<WolfState> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.totalDist, s2.totalDist));
        pq.offer(new WolfState(start, 0, 1));
        d[start][1] = 0;

        while (!pq.isEmpty()) {
            WolfState cur = pq.poll();

            if (cur.totalDist > d[cur.node][cur.isFast]) {
                continue;
            }

            for (Node next : graph.get(cur.node)) {
                int nextDist = cur.totalDist + (cur.isFast == 1 ? next.weight : next.weight * 4);

                int toggledIsFast = cur.isFast == 1 ? 0 : 1;
                if (d[next.to][toggledIsFast] > nextDist) {
                    d[next.to][toggledIsFast] = nextDist;
                    pq.offer(new WolfState(next.to, nextDist, toggledIsFast));
                }
            }
        }

        int[] dist = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dist[i] = Math.min(d[i][0], d[i][1]);
        }

        return dist;
    }
}