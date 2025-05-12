import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] ary = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        SegTree segTree = new SegTree(ary, n);
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());
            if (x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            long sum = segTree.query(1, 0, n - 1, x, y);
            System.out.println(sum);
            long diff = b - segTree.query(1, 0, n - 1, a, a);
            segTree.update(1, 0, n - 1, a, diff);
        }
    }
}

class SegTree {
    long[] tree;
    int size;

    SegTree(int[] ary, int size) {
        this.size = size;
        tree = new long[size * 4];
        init(1, 0, size - 1, ary);
    }

    private long init(int node, int start, int end, int[] ary) {
        if (start == end) {
            return tree[node] = ary[start];
        }

        int mid = (start + end) / 2;
        long leftSum = init(node * 2, start, mid, ary);
        long rightSum = init(node * 2 + 1, mid + 1, end, ary);
        return tree[node] = leftSum + rightSum;
    }

    public long query(int node, int start, int end, int qs, int qe) {
        if (qe < start || end < qs) {
            return 0L;
        }

        if (qs <= start && end <= qe) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        long leftSum = query(node * 2, start, mid, qs, qe);
        long rightSum = query(node * 2 + 1, mid + 1, end, qs, qe);
        return leftSum + rightSum;
    }

    public void update(int node, int start, int end, int index, long diff) {
        if (index < start || end < index) {
            return;
        }
        tree[node] += diff;
        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, index, diff);
        update(node * 2 + 1, mid + 1, end, index, diff);
    }
}
