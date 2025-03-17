import java.util.*;
import java.io.FileInputStream;

class Solution {
    static class Bridge {
        int a;
        int b;
        long cost;

        Bridge(int[] x, int[] y, int a, int b) {
            this.a = a;
            this.b = b;
            long xlen = Math.abs(x[a] - x[b]);
            long ylen = Math.abs(y[a] - y[b]);
            this.cost = xlen * xlen + ylen * ylen;
        }
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/res/re_sample_input.txt"));
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt();
            int[] x = new int[N];
            int[] y = new int[N];
            for (int i = 0; i < N; i++) {
                x[i] = sc.nextInt();
            }
            for (int i = 0; i < N; i++) {
                y[i] = sc.nextInt();
            }
            double E = sc.nextDouble();

            // i번 노드의 x = x[i], y = y[i]

            List<Bridge> bridges = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    bridges.add(new Bridge(x, y, i, j));
                }
            }
            bridges.sort((b1, b2) -> Long.compare(b1.cost, b2.cost));

            int[] parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            long answer = 0;
            int count = 0;
            for (Bridge bridge : bridges) {
                int island1 = bridge.a;
                int island2 = bridge.b;

                if (find(parent, island1) != find(parent, island2)) {
                    union(parent, island1, island2);
                    answer += bridge.cost;
                    count++;
                    if (count == N - 1) {
                        break;
                    }
                }
            }
            System.out.println("#" + test_case + " " + Math.round(answer * E));
        }
    }

    static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent, parent[x]);
    }

    static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }
}