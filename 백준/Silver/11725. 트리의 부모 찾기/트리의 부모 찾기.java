import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] ary = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            ary[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ary[a].add(b);
            ary[b].add(a);
        }

        parent = new int[n + 1];

        dfs(ary, 1, new boolean[n + 1]);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    static void dfs(ArrayList<Integer>[] ary, int start, boolean[] visited) {
        visited[start] = true;
        for (int child : ary[start]) {
            if (visited[child]) {
                continue;
            }
            dfs(ary, child, visited);
            parent[child] = start;
        }
    }

}

// 1 - 6 - 3 - 5
// |
// 4 - 2
// |
// 7

// 1 부터 출발
// parent 배열
// 1 2 3 4 5 6 7
// 4 6 1 3 1 4