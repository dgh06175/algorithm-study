import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // <= 100,000
        int m = Integer.parseInt(st.nextToken()); // <= 100,000

        int[] ary = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ary[i] = Integer.parseInt(br.readLine());
        }
        SegTree tree = new SegTree(ary);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int ans = tree.query(a, b);
            System.out.println(ans);
        }
    }
}

class SegTree {
    int[] tree;
    int size;

    SegTree(int[] ary) {
        size = ary.length;
        tree = new int[size * 4];
        init(ary, 1, 1, size - 1);
    }

    private int init(int[] ary, int node, int start, int end) {
        if (start == end) {
            return tree[node] = ary[start];
        }

        int mid = (start + end) / 2;
        int leftMin = init(ary, node * 2, start, mid);
        int rightMin = init(ary, node * 2 + 1, mid + 1, end);
        return tree[node] = Math.min(leftMin, rightMin);
    }

    public int query(int qs, int qe) {
        return query(1, 1, size - 1, qs, qe);
    }

    private int query(int node, int start, int end, int qs, int qe) {
        if (qe < start || end < qs) {
            return Integer.MAX_VALUE;
        }
        if (qs <= start && end <= qe) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftMin = query(node * 2, start, mid, qs, qe);
        int rightMin = query(node * 2 + 1, mid + 1, end, qs, qe);
        return Math.min(leftMin, rightMin);
    }
}
