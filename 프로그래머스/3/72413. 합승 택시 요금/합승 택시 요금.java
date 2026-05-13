import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<Edge>[] map = new ArrayList[n + 1]; 
        for(int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        for(int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            map[from].add(new Edge(to, cost));
            map[to].add(new Edge(from, cost));
        }
        dijkstra(map, n, 4);
        
        // s -> ? -> A
        // s -> ? -> B
        // s 에서 N 으로 가는 최단거리 + N 에서 A 로 가는 최단거리 + N 에서 B 로 가는 최단거리
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            // if (i == s || i == a || i == b) {
            //     continue;
            // }
            int[] dist_together = dijkstra(map, n, s);
            int together_min = dist_together[i];
            int[] dist_i = dijkstra(map, n, i);
            int a_min = dist_i[a];
            int b_min = dist_i[b];
            if (a_min == Integer.MAX_VALUE || b_min == Integer.MAX_VALUE || together_min == Integer.MAX_VALUE) {
                continue;
            }
            ans = Math.min(ans, together_min + a_min + b_min);
        }
        
        return ans;
    }
    
    int[] dijkstra(List<Edge>[] map, int n, int start) {
        Queue<Edge> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
        pq.offer(new Edge(start, 0));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            int node = now.to;
            int cost = now.cost;
            if (dist[node] < cost) {
                continue;
            }
            
            for(Edge next: map[node]) {
                // if (dist[node] == Integer.MAX_VALUE) {
                //     continue;
                // }
                if (dist[next.to] <= dist[node] + next.cost) {
                    continue;
                }
                dist[next.to] = dist[node] + next.cost;
                pq.offer(new Edge(next.to, dist[node] + next.cost));
            }
            
        }
        
        return dist;
    }
    
    
    class Edge {
        int to;
        int cost;
        
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}

// 플로이드 워셜 -> 200 ^ 3 = 8백만
// 다익스트라 -> 400 * 15 * 400 = 2.4백만