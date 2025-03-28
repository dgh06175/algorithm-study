import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] graph;
    static int[][] dp;
    static int n;
    static final int INF = 987_654_321;
    static int ans = INF;
    static final int START = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());
        graph = new int[n][n];
        dp = new int[n][1 << n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        int ans = dfs(START, 1 << START);
        System.out.println(ans);
    }

    static int dfs(int node, int visited) {
        if (visited == (1 << n) - 1) {
            if (graph[node][START] != 0) {
                return graph[node][START];
            }
            return INF;
        }

        if (dp[node][visited] != -1) {
            return dp[node][visited];
        }

        int minResult = INF;
        for (int i = 0; i < n; i++) {
            if (graph[node][i] == 0) {
                continue;
            }
            if ((visited & (1 << i)) == 0) {
                minResult = Math.min(minResult, dfs(i, visited | (1 << i)) + graph[node][i]);
            }
        }

        return dp[node][visited] = minResult;
    }
}
