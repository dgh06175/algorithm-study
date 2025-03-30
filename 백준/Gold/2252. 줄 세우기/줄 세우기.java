import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] inDegree;
    static Queue<Integer> q;
    static List<Integer> ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        inDegree = new int[n + 1];
        q = new LinkedList<Integer>();
        ans = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            inDegree[b]++;
        }

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            ans.add(x);

            for (int i : graph[x]) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }
        }

        for (int a : ans) {
            System.out.print(a + " ");
        }
    }
}
