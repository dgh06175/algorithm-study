import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // <= 1,000
        int m = Integer.parseInt(st.nextToken()); // <= 100

        int[] degree = new int[n + 1];
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int[] tmp = new int[size];
            for (int j = 0; j < size; j++) {
                tmp[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < size - 1; j++) {
                graph[tmp[j]].add(tmp[j + 1]);
            }
            for (int j = 1; j < size; j++) {
                degree[tmp[j]]++;
            }
        }

        List<Integer> answer = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        for (int t = 0; t < n; t++) {
            if (queue.isEmpty()) {
                break;
            }
            int cur = queue.poll();
            answer.add(cur);
            for (int next : graph[cur]) {
                degree[next]--;
            }
            graph[cur] = List.of();
            for (int i = 1; i <= n; i++) {
                if (degree[i] == 0 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
            }
        }
        if (answer.size() == n) {
            for (int v : answer) {
                System.out.println(v);
            }
        } else {
            System.out.println(0);
        }
    }
}
