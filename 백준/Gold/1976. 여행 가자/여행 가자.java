import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        parent = new int[n + 1];
        parent[0] = -1;
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int k = sc.nextInt();
                if (k == 1)
                    union(i, j);
            }
        }

        int[] plan = new int[m];
        for (int i = 0; i < m; i++) {
            plan[i] = sc.nextInt();
        }

        boolean isValid = true;
        for (int i = 0; i < m - 1; i++) {
            if (find(plan[i]) != find(plan[i + 1])) {
                isValid = false;
            }
        }

        if (isValid) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static private int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static private void union(int x, int y) {
        int rootA = find(x);
        int rootB = find(y);

        if (rootA == rootB)
            return;
        if (rootA < rootB)
            parent[rootB] = parent[rootA];
        else {
            parent[rootA] = parent[rootB];
        }
    }
}