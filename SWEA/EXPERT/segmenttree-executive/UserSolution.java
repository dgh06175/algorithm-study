import java.util.Arrays;

class Tree {
    static class Node {
        int max, sum, index;

        Node() {
        }
    }

    Node[] tree;
    int size;

    Tree(Road[] roads, int n) {
        tree = new Node[n * 4];
        for (int i = 0; i < n * 4; i++) {
            tree[i] = new Node();
        }
        size = n - 1;
        initTree(roads, 1, 0, size - 1);
    }

    private void initTree(Road[] arr, int node, int s, int e) {
        if (s == e) {
            tree[node].max = arr[s].cost;
            tree[node].index = arr[s].index;
            tree[node].sum = arr[s].cost;
            return;
        }

        int mid = (s + e) / 2;
        initTree(arr, node * 2, s, mid);
        initTree(arr, node * 2 + 1, mid + 1, e);

        if (tree[node * 2].max > tree[node * 2 + 1].max) {
            tree[node].max = tree[node * 2].max;
            tree[node].index = tree[node * 2].index;
        } else if (tree[node * 2].max < tree[node * 2 + 1].max) {
            tree[node].max = tree[node * 2 + 1].max;
            tree[node].index = tree[node * 2 + 1].index;
        } else {
            if (tree[node * 2].index < tree[node * 2 + 1].index) {
                tree[node].max = tree[node * 2].max;
                tree[node].index = tree[node * 2].index;
            } else {
                tree[node].max = tree[node * 2 + 1].max;
                tree[node].index = tree[node * 2 + 1].index;
            }
        }
        tree[node].sum = tree[node * 2].sum + tree[node * 2 + 1].sum;
    }

    void update(int node, int s, int e, int index, int diff) {
        if (index < s || e < index)
            return;
        if (s == e) {
            tree[node].max += diff;
            tree[node].sum += diff;
            return;
        }

        int mid = (s + e) / 2;
        update(node * 2, s, mid, index, diff);
        update(node * 2 + 1, mid + 1, e, index, diff);

        if (tree[node * 2].max > tree[node * 2 + 1].max) {
            tree[node].max = tree[node * 2].max;
            tree[node].index = tree[node * 2].index;
        } else if (tree[node * 2].max < tree[node * 2 + 1].max) {
            tree[node].max = tree[node * 2 + 1].max;
            tree[node].index = tree[node * 2 + 1].index;
        } else {
            if (tree[node * 2].index < tree[node * 2 + 1].index) {
                tree[node].max = tree[node * 2].max;
                tree[node].index = tree[node * 2].index;
            } else {
                tree[node].max = tree[node * 2 + 1].max;
                tree[node].index = tree[node * 2 + 1].index;
            }
        }
        tree[node].sum = tree[node * 2].sum + tree[node * 2 + 1].sum;
    }

    int query(int node, int s, int e, int qs, int qe) {
        if (qe < s || e < qs)
            return 0;
        if (qs <= s && e <= qe) {
            return tree[node].sum;
        }

        int mid = (s + e) / 2;
        int left = query(node * 2, s, mid, qs, qe);
        int right = query(node * 2 + 1, mid + 1, e, qs, qe);
        return left + right;
    }
}

class Road {
    int index, cost;

    Road(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

class UserSolution {
    Tree sgTree;
    int[] popu;
    Road[] roads;
    int[] roadLine;
    int n;

    void init(int N, int mPopulation[]) {
        n = N;
        popu = mPopulation;
        roads = new Road[n - 1];
        roadLine = new int[n - 1];
        Arrays.fill(roadLine, 1);
        for (int i = 0; i < n - 1; i++) {
            roads[i] = new Road(i, popu[i] + popu[i + 1]);
        }
        sgTree = new Tree(roads, n);
    }

    int expand(int M) {
        int newCost = -1;
        while (M-- > 0) {
            int oldCost = sgTree.tree[1].max;
            int index = sgTree.tree[1].index;
            roadLine[index]++;
            newCost = (popu[index] + popu[index + 1]) / roadLine[index];
            sgTree.update(1, 0, n - 2, index, newCost - oldCost);
        }
        return newCost;
    }

    int calculate(int mFrom, int mTo) {
        if (mFrom > mTo) {
            int tmp = mFrom;
            mFrom = mTo;
            mTo = tmp;
        }
        int result = sgTree.query(1, 0, n - 2, mFrom, mTo - 1);
        return result;
    }

    int divide(int mFrom, int mTo, int K) {
        int s = 0, e = 1000 * 10010;
        for (int i = mFrom; i <= mTo; i++) {
            s = Math.max(s, popu[i]);
        }

        int ans = e;
        while (s <= e) {
            int mid = (s + e) / 2;
            if (check(mFrom, mTo, mid) <= K) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }

    private int check(int mFrom, int mTo, int mid) {
        int count = 1;
        int sum = 0;
        for (int i = mFrom; i <= mTo; i++) {
            if (sum + popu[i] <= mid) {
                sum += popu[i];
            } else {
                sum = popu[i];
                count++;
            }
        }
        return count;
    }
}