import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parent;
    static boolean[] finished;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            parent = new int[n + 1];
            finished = new boolean[n + 1];
            visited = new boolean[n + 1];
            for (int j = 1; j <= n; j++) {
                parent[j] = Integer.parseInt(st.nextToken());
            }
            count = 0;
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    dfs(j);
                }
            }
            System.out.println(n - count);
        }
    }

    private static void dfs(int x) {
        visited[x] = true;

        int next = parent[x];
        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            count++;
            for (int i = next; i != x; i = parent[i]) {
                count++;
            }
        }
        finished[x] = true;
    }
}
