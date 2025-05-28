import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Node>[] graph;
        for (int i = 0; i < test_case; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph = new List[n + 1];
            for (int j = 0; j < n + 1; j++) {
                graph[j] = new ArrayList<>();
            }
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph[s].add(new Node(e, t));
                graph[e].add(new Node(s, t));
            }

            for (int j = 0; j < w; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph[s].add(new Node(e, -t));
            }
            if (hasMinusCycle(graph, n)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    static boolean hasMinusCycle(List<Node>[] graph, int n) {
        long[] dist = new long[n + 1];
        Arrays.fill(dist, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Node next : graph[j]) {
                    if (dist[next.num] > dist[j] + next.weight) {
                        dist[next.num] = dist[j] + next.weight;
                        if (i == n - 1) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}

class Node {
    int num;
    int weight;

    Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }
}