import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] ary = new int[n];
        int[] maxTree = new int[n * 4];
        int[] minTree = new int[n * 4];
        for (int i = 0; i < n; i++) {
            ary[i] = sc.nextInt();
        }

        initMaxTree(ary, maxTree, 1, 0, n - 1);
        initMinTree(ary, minTree, 1, 0, n - 1);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int min = queryMin(minTree, 1, 0, n - 1, a - 1, b - 1);
            int max = queryMax(maxTree, 1, 0, n - 1, a - 1, b - 1);
            System.out.print(min + " ");
            System.out.println(max);
        }
    }

    private static int initMaxTree(int[] ary, int[] tree, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }
        int mid = (start + end) / 2;
        int left = initMaxTree(ary, tree, node * 2, start, mid);
        int right = initMaxTree(ary, tree, node * 2 + 1, mid + 1, end);
        return tree[node] = Math.max(left, right);
    }

    private static int initMinTree(int[] ary, int[] tree, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }
        int mid = (start + end) / 2;
        int left = initMinTree(ary, tree, node * 2, start, mid);
        int right = initMinTree(ary, tree, node * 2 + 1, mid + 1, end);
        return tree[node] = Math.min(left, right);
    }

    private static int queryMax(int[] tree, int node, int start, int end, int qs, int qe) {
        if (qe < start || qs > end)
            return Integer.MIN_VALUE;
        if (qs <= start && end <= qe)
            return tree[node];

        int mid = (start + end) / 2;
        int left = queryMax(tree, node * 2, start, mid, qs, qe);
        int right = queryMax(tree, node * 2 + 1, mid + 1, end, qs, qe);

        return Math.max(left, right);
    }

    private static int queryMin(int[] tree, int node, int start, int end, int qs, int qe) {
        if (qe < start || qs > end)
            return Integer.MAX_VALUE;
        if (qs <= start && end <= qe)
            return tree[node];

        int mid = (start + end) / 2;
        int left = queryMin(tree, node * 2, start, mid, qs, qe);
        int right = queryMin(tree, node * 2 + 1, mid + 1, end, qs, qe);

        return Math.min(left, right);
    }
}