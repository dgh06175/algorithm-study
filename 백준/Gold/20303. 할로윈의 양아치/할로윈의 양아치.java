import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Group {
    int peopleCount;
    int candyCount;

    Group(int peopleCount, int candyCount) {
        this.peopleCount = peopleCount;
        this.candyCount = candyCount;
    }
}

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int k = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        int[] c = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            c[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        Map<Integer, Group> groupMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int root = find(i);
            groupMap.putIfAbsent(root, new Group(0, 0));
            Group g = groupMap.get(root);
            g.peopleCount += 1;
            g.candyCount += c[i];
        }

        List<Group> groups = groupMap.values().stream().collect(Collectors.toList());
        int[] dp = new int[k]; // dp[i] = i명 뺏어서 가장 많은 사탕 개수

        for (Group group : groups) {
            int w = group.peopleCount;
            int v = group.candyCount;

            for (int i = k - 1; i >= w; i--) {
                dp[i] = Math.max(dp[i], dp[i - w] + v);
            }
        }
        System.out.println(dp[k - 1]);
    }

    private static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return;
        if (x < y) {
            parent[rootY] = rootX;
        } else {
            parent[rootX] = rootY;
        }
    }
}