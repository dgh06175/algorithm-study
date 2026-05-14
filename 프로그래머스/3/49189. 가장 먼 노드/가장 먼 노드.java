import java.util.*;

class Solution {
    private static final int MAX_VALUE = 100_000_000;
    
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }
        
        int[] d = bfs(graph, n, 1);
        int max_value = 0;
        for(int i = 1; i < n + 1; i++) {
            max_value = Math.max(d[i], max_value);
        }
        
        int answer = 0;
        for(int i = 1; i < n + 1; i++) {
            if (d[i] == max_value) {
                answer += 1;
            }
        }
        
        return answer;
    }
    
    int[] bfs(List<Integer>[] graph, int n, int start) {
        int[] d = new int[n + 1];
        Arrays.fill(d, MAX_VALUE);
        d[start] = 0;
        boolean[] visited = new boolean[n+1];
        visited[start] = true;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            
            for(int next: graph[now]) {
                if (visited[next]) {
                    continue;
                }
                d[next] = Math.min(d[next], d[now] + 1);
                
                queue.offer(next);
                visited[next] = true;
            }
        }
        
        
        return d;
    }
}