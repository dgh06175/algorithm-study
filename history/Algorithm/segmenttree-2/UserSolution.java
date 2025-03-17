import java.util.Arrays;

class Tree {
    int[] minTree;
    int[] maxTree;
    long[] sumTree;
    int[] lazy;
    int n;

    Tree(int[] arr, int n) {
        this.n = n;
        minTree = new int[n << 2];
        maxTree = new int[n << 2];
        sumTree = new long[n << 2];
        lazy = new int[n << 2];
        init(arr, 1, 0, n - 1);
    }

    private void init(int[] arr, int node, int s, int e) {
        if (s == e) {
            minTree[node] = arr[s];
            maxTree[node] = arr[s];
            sumTree[node] = arr[s];
            return;
        }

        int mid = (s + e) >> 1;
        init(arr, node << 1, s, mid);
        init(arr, node << 1 | 1, mid + 1, e);

        minTree[node] = Math.min(minTree[node << 1], minTree[node << 1 | 1]);
        maxTree[node] = Math.max(maxTree[node << 1], maxTree[node << 1 | 1]);
        sumTree[node] = sumTree[node << 1] + sumTree[node << 1 | 1];
    }

    public void update(int start, int end, int diff) {
        update(1, 0, n - 1, start, end, diff);
    }

    private void update(int node, int s, int e, int us, int ue, int diff) {
        if (lazy[node] != 0) {
            minTree[node] += lazy[node];
            maxTree[node] += lazy[node];
            sumTree[node] += lazy[node] * (e - s + 1);

            if (s != e) {
                lazy[node << 1] += lazy[node];
                lazy[node << 1 | 1] += lazy[node];
            }
            lazy[node] = 0;
        }
        if (ue < s || e < us)
            return;
        if (us <= s && e <= ue) {
            minTree[node] += diff;
            maxTree[node] += diff;
            sumTree[node] += diff * (e - s + 1);

            if (s != e) {
                lazy[node << 1] += diff;
                lazy[node << 1 | 1] += diff;
            }
            return;
        }

        int mid = (s + e) / 2;
        update(node << 1, s, mid, us, ue, diff);
        update(node << 1 | 1, mid + 1, e, us, ue, diff);

        minTree[node] = Math.min(minTree[node << 1], minTree[node << 1 | 1]);
        maxTree[node] = Math.max(maxTree[node << 1], maxTree[node << 1 | 1]);
        sumTree[node] = sumTree[node << 1] + sumTree[node << 1 | 1];
    }
}

class UserSolution {
    Tree tree;
    int c;

    void init(int C) {
        c = C;
        this.tree = new Tree(new int[c], c);
    }

    Solution.Result dropBlocks(int mCol, int mHeight, int mLength) {
        tree.update(mCol, mCol + mLength - 1, mHeight);
        if (tree.minTree[1] != 0) {
            tree.update(0, c, -tree.minTree[1]);
        }

        Solution.Result ret = new Solution.Result();

        ret.count = (int) (tree.sumTree[1] % 1_000_000L);
        ret.top = tree.maxTree[1];

        return ret;
    }
}