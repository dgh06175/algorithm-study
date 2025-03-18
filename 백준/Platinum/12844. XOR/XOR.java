import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] ary = new int[n];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        LazySegmentTree tree = new LazySegmentTree(ary);

        int m = Integer.parseInt(bf.readLine());
        for (int l = 0; l < m; l++) {
            st = new StringTokenizer(bf.readLine());
            int type = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int k = Integer.parseInt(st.nextToken());
                tree.update(i, j, k);
            } else if (type == 2) {
                System.out.println(tree.query(i, j));
            }
        }
    }
}

class LazySegmentTree {
    int[] lazy;
    int[] tree;
    int size;

    LazySegmentTree(int[] ary) {
        this.size = ary.length;
        this.lazy = new int[size * 4];
        this.tree = new int[size * 4];
        init(ary, 1, 0, size - 1);
    }

    private int init(int[] ary, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }

        int mid = (start + end) / 2;
        int left = init(ary, node * 2, start, mid);
        int right = init(ary, node * 2 + 1, mid + 1, end);
        return tree[node] = left ^ right;
    }

    public void update(int us, int ue, int diff) {
        update(1, 0, size - 1, us, ue, diff);
    }

    private void update(int node, int start, int end, int us, int ue, int diff) {
        propagate(node, start, end);

        if (ue < start || end < us) {
            return;
        }

        if (us <= start && end <= ue) {
            lazy[node] ^= diff;
            propagate(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, us, ue, diff);
        update(node * 2 + 1, mid + 1, end, us, ue, diff);
        tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
    }

    public int query(int qs, int qe) {
        return query(1, 0, size - 1, qs, qe);
    }

    private int query(int node, int start, int end, int qs, int qe) {
        propagate(node, start, end);

        if (qe < start || end < qs) {
            return 0;
        }

        if (qs <= start && end <= qe) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int left = query(node * 2, start, mid, qs, qe);
        int right = query(node * 2 + 1, mid + 1, end, qs, qe);
        return left ^ right;
    }

    private void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            if ((end - start + 1) % 2 == 1) {
                tree[node] ^= lazy[node];
            }
            if (start != end) {
                lazy[node * 2] ^= lazy[node];
                lazy[node * 2 + 1] ^= lazy[node];
            }
            lazy[node] = 0;
        }
    }
}