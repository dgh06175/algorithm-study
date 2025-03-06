class UserSolution {
    int[] tree;
    int[] arr;
    int[] type;
    int n;

    void init(int N, int M, int mType[], int mTime[]) {
        n = N;
        tree = new int[n * 4];
        type = mType;
        arr = mTime;

        initTree(tree, arr, 1, 0, n - 2);
    }

    void destroy() {
        type = new int[n - 1];
        arr = new int[n - 1];
        tree = new int[n * 4];
    }

    void update(int mID, int mNewTime) {
        updateTree(tree, 1, 0, n - 2, mID, mNewTime - arr[mID]);
        arr[mID] = mNewTime;
    }

    int updateByType(int mTypeID, int mRatio256) {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            if (type[i] != mTypeID)
                continue;
            int diff = arr[i] * mRatio256 / 256 - arr[i];
            arr[i] += diff;
            sum += arr[i];
            updateTree(tree, 1, 0, n - 2, i, diff);
        }
        return sum;
    }

    int calculate(int mA, int mB) {
        if (mA > mB) {
            int tmp = mA;
            mA = mB;
            mB = tmp;
        }
        int sum = queryTree(tree, 1, 0, n - 2, mA, mB - 1);
        return sum;
    }

    private int initTree(int[] tree, int[] ary, int node, int s, int e) {
        if (s == e)
            return tree[node] = ary[s];

        int mid = (s + e) / 2;
        int left = initTree(tree, ary, node * 2, s, mid);
        int right = initTree(tree, ary, node * 2 + 1, mid + 1, e);
        return tree[node] = left + right;
    }

    private void updateTree(int[] tree, int node, int s, int e, int index, int diff) {
        if (index < s || e < index) {
            return;
        }
        tree[node] += diff;

        if (s == e)
            return;

        int mid = (s + e) / 2;
        updateTree(tree, node * 2, s, mid, index, diff);
        updateTree(tree, node * 2 + 1, mid + 1, e, index, diff);
    }

    private int queryTree(int[] tree, int node, int s, int e, int qs, int qe) {
        if (qs > e || qe < s)
            return 0;
        if (qs <= s && e <= qe)
            return tree[node];
        if (s == e)
            return tree[node];

        int mid = (s + e) / 2;
        int left = queryTree(tree, node * 2, s, mid, qs, qe);
        int right = queryTree(tree, node * 2 + 1, mid + 1, e, qs, qe);
        return left + right;
    }
}
