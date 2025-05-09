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

        LazyTree lazyTree = new LazyTree(n);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (o == 0) {
                lazyTree.update(s, t);
            } else {
                int sum = lazyTree.query(s, t);
                System.out.println(sum);
            }
        }
    }
}

class LazyTree {
    int[] tree;
    boolean[] lazy;
    int n;

    LazyTree(int size) {
        n = size;
        tree = new int[size * 4];
        lazy = new boolean[size * 4];
    }

    public void update(int us, int ue) {
        update(1, 0, n, us, ue);
    }

    private void update(int node, int start, int end, int us, int ue) {
        propagate(node, start, end);
        if (ue < start || end < us) {
            return;
        }

        // 업데이트 범위에 포함되면 tree에 값 적용하고 양쪽 자식의 lazy 값 더하기
        if (us <= start && end <= ue) {
            lazy[node] = !lazy[node];
            propagate(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        update(node * 2, start, mid, us, ue);
        update(node * 2 + 1, mid + 1, end, us, ue);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public int query(int qs, int qe) {
        return query(1, 0, n, qs, qe);
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
        return left + right;
    }

    private void propagate(int node, int start, int end) {
        if (!lazy[node]) {
            return;
        }
        tree[node] = (end - start + 1) - tree[node];
        if (start != end) {
            lazy[node * 2] = !lazy[node * 2];
            lazy[node * 2 + 1] = !lazy[node * 2 + 1];
        }

        lazy[node] = false;
    }
}
