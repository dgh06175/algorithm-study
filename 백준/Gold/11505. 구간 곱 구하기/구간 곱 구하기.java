import java.util.*;
import java.io.*;

class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] ary = new long[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Long.parseLong(br.readLine());
        }
        long[] timeTree = new long[n * 4];
        initTimeTree(ary, timeTree, 1, 0, n - 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                updateTime(timeTree, 1, 0, n - 1, b - 1, c);
            } else if (a == 2) {
                long result = queryTime(timeTree, 1, 0, n - 1, b - 1, c - 1);
                System.out.println(result);
            }
        }
    }

    private static long initTimeTree(long[] ary, long[] tree, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }
        int mid = (start + end) / 2;
        long left = initTimeTree(ary, tree, node * 2, start, mid);
        long right = initTimeTree(ary, tree, node * 2 + 1, mid + 1, end);
        return tree[node] = (left * right) % MOD;
    }

    private static void updateTime(long[] tree, int node, int start, int end, int index, int value) {
        if (index < start || index > end)
            return;
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        updateTime(tree, node * 2, start, mid, index, value);
        updateTime(tree, node * 2 + 1, mid + 1, end, index, value);
        tree[node] = (tree[node * 2] * tree[node * 2 + 1]) % MOD;
    }

    private static long queryTime(long[] tree, int node, int start, int end, int qs, int qe) {
        if (qe < start || qs > end)
            return 1;
        if (qs <= start && end <= qe)
            return tree[node];

        int mid = (start + end) / 2;
        long left = queryTime(tree, node * 2, start, mid, qs, qe);
        long right = queryTime(tree, node * 2 + 1, mid + 1, end, qs, qe);

        return (left * right) % MOD;
    }
}