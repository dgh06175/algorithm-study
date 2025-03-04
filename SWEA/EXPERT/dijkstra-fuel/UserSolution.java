import java.util.*;

// 0~n+1인 도시가 있다.
// init 함수에는 도시에 대응하는 cost를 가진 mCost 배열이 주어진다.
// 각 도로를 표현하는 K개의 도로의 아이디, 시작점, 끝점, 거리를 가진 각 배열 4개가 추가로 주어진다.
// 도로는 일반적으로 사이클을 포함할 수 있다.
//
// 각 도시에서 전기차를 충전할 수있다. 용량은 무한이다. 각 도시에서는 단위거리당 cost 의 비용으로 전기를 충전할 수 있다.
// 코스트가 낮은 도시에서 미리 충전해가면 이득이다.
//
// 다음 함수 4개를 구현하라.
// 0. init 함수
// 1. 도로를 추가하는 함수
// 2. id로 도로 삭제하는 함수
// 3. 도시 s 에서 e로 가는 최단 비용

class UserSolution {
    class Road {
        int from, to, dist;

        Road(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    class State {
        int node, minCost, totalDist;

        State(int node, int minCost, int minDist) {
            this.node = node;
            this.minCost = minCost;
            this.totalDist = minDist;
        }
    }

    private int n;
    private int[] mCost;
    private List<Road>[] graph;
    private Map<Integer, Road> roadId;

    // 초기화 함수: n은 도시 수, m은 초기 도로의 개수
    // mCost: 각 도시의 충전 비용, mId, mStart, mEnd, mDist: 각 도로의 id, 시작점, 끝점, 거리
    void init(int n, int m, int[] mCost, int[] mId, int[] mStart, int[] mEnd, int[] mDist) {
        this.n = n;
        this.mCost = mCost;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        roadId = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Road road = new Road(mStart[i], mEnd[i], mDist[i]);
            roadId.put(mId[i], road);
            graph[mStart[i]].add(road);
        }
    }

    // 도로 추가: id, 시작점 s, 끝점 e, 거리 dist
    // 추가시 도로 id 해시맵에도 저장
    void addRoad(int id, int s, int e, int dist) {
        Road road = new Road(s, e, dist);
        roadId.put(id, road);
        graph[s].add(road);
    }

    // 도로 삭제: 주어진 id의 도로를 삭제
    void removeRoad(int id) {
        Road road = roadId.get(id);
        graph[road.from].remove(road);
        roadId.remove(id);
    }

    // 도시 s에서 e까지 이동하는 최소 비용을 구하는 함수
    // 각 구간의 이동 비용은 (현재까지의 최저 연료 단가 × 도로 길이)로 계산
    int getMinCost(int s, int e) {
        List<Map<Integer, Integer>> dists = dijkstra(s);
        int min_dist = Integer.MAX_VALUE;
        for (int state : dists.get(e).values()) {
            min_dist = Math.min(state, min_dist);
        }
        return min_dist;
    }

    private List<Map<Integer, Integer>> dijkstra(int start) {
        List<Map<Integer, Integer>> dists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dists.add(new HashMap<>());
        }
        Queue<State> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.totalDist, s2.totalDist));
        pq.offer(new State(start, mCost[start], 0));

        while (!pq.isEmpty()) {
            State state = pq.poll();
            int node = state.node;
            int minCost = state.minCost;
            int totalDist = state.totalDist;

            Integer dist = dists.get(node).get(minCost);
            if (dist != null && dist < totalDist) {
                continue;
            }

            for (Road road : graph[node]) {
                int newMinCost = Math.min(minCost, mCost[road.to]);
                int newDist = totalDist + minCost * road.dist;
                Map<Integer, Integer> nextDist = dists.get(road.to);
                if (!nextDist.containsKey(newMinCost) || nextDist.get(newMinCost) > newDist) {
                    pq.offer(new State(road.to, newMinCost, newDist));
                    nextDist.put(newMinCost, newDist);
                }
            }
        }

        return dists;
    }
}
