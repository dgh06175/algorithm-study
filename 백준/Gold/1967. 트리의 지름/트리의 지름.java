import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        List<int[]>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[parent].add(new int[] { child, weight });
            graph[child].add(new int[] { parent, weight });
        }
        int[] tmp = getMaxLen(graph, 1);
        int[] ans = getMaxLen(graph, tmp[0]);
        System.out.println(ans[1]);
    }

    static int[] getMaxLen(List<int[]>[] graph, int start) {
        int[] maxWeightNode = new int[2];
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] { start, 0 });
        while (!stack.empty()) {
            int[] cur = stack.pop();
            if (maxWeightNode[1] < cur[1]) {
                maxWeightNode = cur;
            }

            for (int[] next : graph[cur[0]]) {
                if (!visited[next[0]]) {
                    stack.push(new int[] { next[0], next[1] + cur[1] });
                    visited[next[0]] = true;
                }
            }
        }
        return maxWeightNode;
    }
}