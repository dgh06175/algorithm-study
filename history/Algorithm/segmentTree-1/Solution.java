package history.EXPERT.segmentTree;

import java.util.*;
import java.io.*;

class Solution {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/res/sample_in.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.printf("#%d", test_case);
            int n = sc.nextInt();
            int q = sc.nextInt();
            long[] a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextLong();
            }

            long[] maxTree = new long[n * 4];
            long[] minTree = new long[n * 4];

            initMaxTree(a, maxTree, 1, 0, n - 1);
            initMinTree(a, minTree, 1, 0, n - 1);

            for (int j = 0; j < q; j++) {
                int query = sc.nextInt();
                if (query == 0) {
                    int i = sc.nextInt();
                    long x = sc.nextLong();
                    updateMax(maxTree, 1, 0, n - 1, i, x);
                    updateMin(minTree, 1, 0, n - 1, i, x);
                } else if (query == 1) {
                    int l = sc.nextInt();
                    int r = sc.nextInt();
                    long max = queryMax(maxTree, 1, 0, n - 1, l, r - 1);
                    long min = queryMin(minTree, 1, 0, n - 1, l, r - 1);
                    System.out.print(" " + (max - min));
                }
            }
            System.out.println();
        }
    }

    private static long initMaxTree(long[] ary, long[] tree, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }
        int mid = (start + end) / 2;
        long left = initMaxTree(ary, tree, node * 2, start, mid);
        long right = initMaxTree(ary, tree, node * 2 + 1, mid + 1, end);
        return tree[node] = Math.max(left, right);
    }

    private static long initMinTree(long[] ary, long[] tree, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }
        int mid = (start + end) / 2;
        long left = initMinTree(ary, tree, node * 2, start, mid);
        long right = initMinTree(ary, tree, node * 2 + 1, mid + 1, end);
        return tree[node] = Math.min(left, right);
    }

    private static void updateMax(long[] tree, int node, int start, int end, int index, long value) {
        if (index < start || index > end)
            return;
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        updateMax(tree, node * 2, start, mid, index, value);
        updateMax(tree, node * 2 + 1, mid + 1, end, index, value);
        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    private static void updateMin(long[] tree, int node, int start, int end, int index, long value) {
        if (index < start || index > end)
            return;
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        updateMin(tree, node * 2, start, mid, index, value);
        updateMin(tree, node * 2 + 1, mid + 1, end, index, value);
        tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
    }

    private static long queryMax(long[] tree, int node, int start, int end, int qs, int qe) {
        if (qe < start || qs > end)
            return Long.MIN_VALUE;
        if (qs <= start && end <= qe)
            return tree[node];

        int mid = (start + end) / 2;
        long left = queryMax(tree, node * 2, start, mid, qs, qe);
        long right = queryMax(tree, node * 2 + 1, mid + 1, end, qs, qe);

        return Math.max(left, right);
    }

    private static long queryMin(long[] tree, int node, int start, int end, int qs, int qe) {
        if (qe < start || qs > end)
            return Long.MAX_VALUE;
        if (qs <= start && end <= qe)
            return tree[node];

        int mid = (start + end) / 2;
        long left = queryMin(tree, node * 2, start, mid, qs, qe);
        long right = queryMin(tree, node * 2 + 1, mid + 1, end, qs, qe);

        return Math.min(left, right);
    }
}