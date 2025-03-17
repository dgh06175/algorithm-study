import java.util.*;

class UserSolution {
    class Node {
        int to, time;

        Node(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    class State {
        int node, time;

        State(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    List<List<Node>> graph;
    int n, k;

    public void init(int N, int K, int mNodeA[], int mNodeB[], int mTime[]) {
        this.n = N;
        this.k = K;
        graph = new ArrayList<>(30031);
        for (int i = 0; i < 30031; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < K; i++) {
            graph.get(mNodeA[i]).add(new Node(mNodeB[i], mTime[i]));
            graph.get(mNodeB[i]).add(new Node(mNodeA[i], mTime[i]));
        }
    }

    public void addLine(int mNodeA, int mNodeB, int mTime) {
        graph.get(mNodeA).add(new Node(mNodeB, mTime));
        graph.get(mNodeB).add(new Node(mNodeA, mTime));
    }

    public void removeLine(int mNodeA, int mNodeB) {
        graph.get(mNodeA).removeIf(n -> n.to == mNodeB);
        graph.get(mNodeB).removeIf(n -> n.to == mNodeA);
    }

    int count = 0;

    public int checkTime(int mNodeA, int mNodeB) {
        int[] minTime = dijkstra(mNodeA);
        // System.out.println(++count + ": " + minTime[mNodeB]);
        return minTime[mNodeB];
    }

    private int[] dijkstra(int start) {
        int[] dist = new int[30000];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<State> queue = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.time, s2.time));
        queue.offer(new State(start, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int curNode = cur.node;
            int curTime = cur.time;

            if (dist[curNode] != curTime)
                continue;

            for (Node node : graph.get(curNode)) {
                int nextNode = node.to;
                int nextTime = node.time;
                int calcTime = nextTime + curTime;
                if (dist[nextNode] > calcTime) {
                    dist[nextNode] = calcTime;
                    queue.offer(new State(nextNode, calcTime));
                }
            }
        }

        return dist;
    }
}