import java.util.*;

class Solution {
    int ans = 0;
    int n = 0;
    
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
        }
        dfs(info, graph, 0, 0, 0, new boolean[n]);
        
        return ans;
    }
    
    void dfs(int[] info, List<Integer>[] graph, int node, int sheep_count, int wolf_count, boolean[] next_visit) {
        if (info[node] == 0) {
            sheep_count += 1;
        } else {
            wolf_count += 1;
        }
        
        if (sheep_count <= wolf_count) {
            return;
        }
        ans = Math.max(ans, sheep_count);
        
        next_visit[node] = false;
        
        for(int next: graph[node]) {
            next_visit[next] = true;
        }
        
        for(int i = 0; i < n; i++) {
            if (next_visit[i]) {
                dfs(info, graph, i, sheep_count, wolf_count, next_visit.clone());
            }
        }
    }
}