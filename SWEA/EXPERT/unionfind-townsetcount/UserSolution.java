import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/res/s_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // ~100
            int M = sc.nextInt(); // ~5000
            int[] parent = new int[N + 1];
            for (int i = 0; i < N + 1; i++) {
                parent[i] = i;
            }

            while (M-- > 0) {
                int a = sc.nextInt(); // 5
                int b = sc.nextInt(); // 1

                // Union
                int rootA = find(a, parent);
                int rootB = find(b, parent);

                if (rootA == rootB) {
                    continue;
                }
                parent[rootB] = rootA;
            }

            Set<Integer> roots = new HashSet<>();
            for (int i = 1; i < N + 1; i++) {
                roots.add(find(i, parent));
            }
            System.out.println("#" + test_case + " " + roots.size());
        }
    }

    static int find(int x, int[] parent) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x], parent);
    }
}