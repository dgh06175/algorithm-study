import java.util.*;
import java.io.*;

class Main {
    static int n;
    static List<List<Integer>> reverse_graph;
    static int d[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            d = new int[n + 1];
            reverse_graph = new ArrayList<>();
            reverse_graph.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                reverse_graph.add(new ArrayList<>());
                d[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                reverse_graph.get(y).add(x);
            }
            int w = Integer.parseInt(br.readLine());

            // dp[i] = i 번 노드의 완성 최소 시간
            int[] dp = new int[n + 1];
            Arrays.fill(dp, Integer.MIN_VALUE);

            dfs(dp, w);
            System.out.println(dp[w]);
        }
    }

    // 너비 우선 탐색으로 w 에서 출발해서 다음 노드가 없는 노드 까지의 최대 거리 구하기
    // (단, 마지막 노드에서는 d[node] 더해야 함, 그 전에는 모든 간선의 가중치는 출발점의 d값)
    static int dfs(int[] dp, int node) {
        if (dp[node] != Integer.MIN_VALUE) {
            return dp[node];
        }
        if (reverse_graph.get(node).isEmpty()) {
            return dp[node] = d[node];
        }

        int max = 0;
        for (int next : reverse_graph.get(node)) {
            max = Math.max(max, dfs(dp, next));
        }

        return dp[node] = max + d[node];
    }
}