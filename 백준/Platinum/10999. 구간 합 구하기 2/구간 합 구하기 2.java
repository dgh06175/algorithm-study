import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] ary = new long[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Long.parseLong(br.readLine());
        }
        LazySegmentTree sgTree = new LazySegmentTree(ary);
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {
                b--;
                c--;
                long d = Long.parseLong(st.nextToken());
                sgTree.update(b, c, d);
            } else if (a == 2) {
                b--;
                c--;
                long ans = sgTree.query(b, c);
                System.out.println(ans);
            }
        }
    }
}

class LazySegmentTree {
    long[] lazy;
    long[] tree;
    int size;

    LazySegmentTree(long[] ary) {
        this.size = ary.length;
        this.lazy = new long[size * 4];
        this.tree = new long[size * 4];
        init(ary, 1, 0, size - 1);
    }

    private void init(long[] ary, int node, int start, int end) {
        if (start == end) {
            tree[node] = ary[start];
            return;
        }

        int mid = (start + end) / 2;
        init(ary, node * 2, start, mid);
        init(ary, node * 2 + 1, mid + 1, end);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public void update(int us, int ue, long diff) {
        update(1, 0, size - 1, us, ue, diff);
    }

    private void update(int node, int start, int end, int us, int ue, long diff) {
        propagate(node, start, end);
        if (ue < start || end < us) {
            return;
        }
        if (us <= start && end <= ue) {
            lazy[node] += diff;
            propagate(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, us, ue, diff);
        update(node * 2 + 1, mid + 1, end, us, ue, diff);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public long query(int qs, int qe) {
        long ans = query(1, 0, size - 1, qs, qe);
        return ans;
    }

    private long query(int node, int start, int end, int qs, int qe) {
        propagate(node, start, end);
        if (qe < start || end < qs) {
            return 0;
        }
        if (qs <= start && end <= qe) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        long left = query(node * 2, start, mid, qs, qe);
        long right = query(node * 2 + 1, mid + 1, end, qs, qe);
        return left + right;
    }

    private void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }
}