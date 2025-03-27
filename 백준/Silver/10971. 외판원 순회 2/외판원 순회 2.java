import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int ans = Integer.MAX_VALUE;
    static final int START = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int visited = 1 << START;
        dfs(graph, START, 0, visited);
        System.out.println(ans);
    }

    static void dfs(int[][] graph, int node, int cost, int visited) {
        // 모든 노드 방문 시
        if (visited == (1 << n) - 1) {
            if (graph[node][START] != 0) {
                ans = Math.min(ans, cost + graph[node][START]);
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            if (graph[node][i] == 0) {
                continue;
            }
            if ((visited & (1 << i)) == 0) {
                dfs(graph, i, cost + graph[node][i], visited | (1 << i));
            }
        }
    }
}
