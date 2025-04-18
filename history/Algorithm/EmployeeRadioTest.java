import java.util.*;

public class EmployeeRadioTest {
    // 공통 인터페이스
    public interface EmployeeRadio {
        void add(int id, int start, int end);

        void remove(int id);

        int count(int mBSTime);
    }

    // 1) 정렬된 리스트 기반 구현
    public static class EmployeeRadioSimple implements EmployeeRadio {
        private final int mTime;
        private final List<Employee> list = new ArrayList<>();
        private final Map<Integer, Employee> map = new HashMap<>();

        public EmployeeRadioSimple(int mTime) {
            this.mTime = mTime;
        }

        @Override
        public void add(int id, int start, int end) {
            Employee e = new Employee(id, start, end);
            int idx = Collections.binarySearch(list, e, Comparator.comparingInt(emp -> emp.start));
            if (idx < 0)
                idx = -idx - 1;
            list.add(idx, e);
            map.put(id, e);
        }

        @Override
        public void remove(int id) {
            Employee e = map.remove(id);
            if (e != null)
                list.remove(e);
        }

        @Override
        public int count(int mBSTime) {
            int t2 = mBSTime + mTime;
            Employee key = new Employee(0, mBSTime, 0);
            int idx = Collections.binarySearch(list, key, Comparator.comparingInt(emp -> emp.start));
            int pos;
            if (idx >= 0) {
                pos = idx;
                while (pos + 1 < list.size() && list.get(pos + 1).start == mBSTime)
                    pos++;
            } else {
                pos = -idx - 2;
            }
            if (pos < 0)
                return 0;
            int cnt = 0;
            for (int i = 0; i <= pos; i++) {
                if (list.get(i).end >= t2)
                    cnt++;
            }
            return cnt;
        }

        private static class Employee {
            int id, start, end;

            Employee(int id, int start, int end) {
                this.id = id;
                this.start = start;
                this.end = end;
            }

            @Override
            public boolean equals(Object o) {
                return o instanceof Employee && ((Employee) o).id == id;
            }

            @Override
            public int hashCode() {
                return Integer.hashCode(id);
            }
        }
    }

    // 2) 2D Fenwick Tree 기반 구현
    public static class EmployeeRadioFenwick implements EmployeeRadio {
        private final int mTime, S, E;
        private final int[] xs, ys;
        private final BIT[] tree;
        private final Map<Integer, int[]> idToXY = new HashMap<>();

        public EmployeeRadioFenwick(int mTime, int[] allStarts, int[] allEnds) {
            this.mTime = mTime;
            this.xs = Arrays.stream(allStarts).distinct().sorted().toArray();
            this.ys = Arrays.stream(allEnds).distinct().sorted().toArray();
            this.S = xs.length;
            this.E = ys.length;
            tree = new BIT[S + 1];
            for (int i = 1; i <= S; i++)
                tree[i] = new BIT(E);
        }

        @Override
        public void add(int id, int start, int end) {
            int x = Arrays.binarySearch(xs, start);
            x++;
            int y = Arrays.binarySearch(ys, end);
            y++;
            idToXY.put(id, new int[] { x, y });
            for (int i = x; i <= S; i += i & -i)
                tree[i].update(y, +1);
        }

        @Override
        public void remove(int id) {
            int[] xy = idToXY.remove(id);
            if (xy == null)
                return;
            int x = xy[0], y = xy[1];
            for (int i = x; i <= S; i += i & -i)
                tree[i].update(y, -1);
        }

        @Override
        public int count(int mBSTime) {
            int t2 = mBSTime + mTime;
            int xi = Arrays.binarySearch(xs, mBSTime);
            if (xi < 0)
                xi = -xi - 2;
            if (xi < 0)
                return 0;
            int xLim = xi + 1;
            int yi = Arrays.binarySearch(ys, t2);
            if (yi < 0)
                yi = -yi - 2;
            int total = query(xLim, E);
            int less = (yi >= 0 ? query(xLim, yi + 1) : 0);
            return total - less;
        }

        private int query(int xLim, int y) {
            int sum = 0;
            for (int i = xLim; i > 0; i -= i & -i)
                sum += tree[i].sum(y);
            return sum;
        }

        private static class BIT {
            private final int n;
            private final int[] t;

            BIT(int n) {
                this.n = n;
                t = new int[n + 1];
            }

            void update(int i, int v) {
                for (; i <= n; i += i & -i)
                    t[i] += v;
            }

            int sum(int i) {
                int s = 0;
                for (; i > 0; i -= i & -i)
                    s += t[i];
                return s;
            }
        }
    }

    // 3) Segment Tree 기반 구현
    public static class EmployeeRadioSegTree implements EmployeeRadio {
        private final int mTime, S, E;
        private final int[] xs, ys;
        private final BIT[] seg;
        private final Map<Integer, int[]> idToXY = new HashMap<>();

        public EmployeeRadioSegTree(int mTime, int[] allStarts, int[] allEnds) {
            this.mTime = mTime;
            this.xs = Arrays.stream(allStarts).distinct().sorted().toArray();
            this.ys = Arrays.stream(allEnds).distinct().sorted().toArray();
            this.S = xs.length;
            this.E = ys.length;
            int size = 4 * S;
            seg = new BIT[size];
            for (int i = 0; i < size; i++)
                seg[i] = new BIT(E);
        }

        @Override
        public void add(int id, int start, int end) {
            int x = Arrays.binarySearch(xs, start) + 1;
            int y = Arrays.binarySearch(ys, end) + 1;
            idToXY.put(id, new int[] { x, y });
            update(1, 1, S, x, y, +1);
        }

        @Override
        public void remove(int id) {
            int[] xy = idToXY.remove(id);
            if (xy == null)
                return;
            update(1, 1, S, xy[0], xy[1], -1);
        }

        @Override
        public int count(int mBSTime) {
            int t2 = mBSTime + mTime;
            int xi = Arrays.binarySearch(xs, mBSTime);
            if (xi < 0)
                xi = -xi - 2;
            if (xi < 0)
                return 0;
            int xLim = xi + 1;
            int yi = Arrays.binarySearch(ys, t2);
            if (yi < 0)
                yi = -yi - 2;
            int total = query(1, 1, S, 1, xLim, E);
            int less = (yi >= 0 ? query(1, 1, S, 1, xLim, yi + 1) : 0);
            return total - less;
        }

        private void update(int node, int l, int r, int idx, int y, int v) {
            if (idx < l || idx > r)
                return;
            seg[node].update(y, v);
            if (l == r)
                return;
            int mid = (l + r) >> 1;
            update(node << 1, l, mid, idx, y, v);
            update(node << 1 | 1, mid + 1, r, idx, y, v);
        }

        private int query(int node, int l, int r, int ql, int qr, int y) {
            if (qr < l || r < ql)
                return 0;
            if (ql <= l && r <= qr)
                return seg[node].sum(y);
            int mid = (l + r) >> 1;
            return query(node << 1, l, mid, ql, qr, y)
                    + query(node << 1 | 1, mid + 1, r, ql, qr, y);
        }

        private static class BIT {
            private final int n;
            private final int[] t;

            BIT(int n) {
                this.n = n;
                t = new int[n + 1];
            }

            void update(int i, int v) {
                for (; i <= n; i += i & -i)
                    t[i] += v;
            }

            int sum(int i) {
                int s = 0;
                for (; i > 0; i -= i & -i)
                    s += t[i];
                return s;
            }
        }
    }

    public static class EmployeeRadioLazy implements EmployeeRadio {
        private static final int MAX_TIME = 2_000_000;
        private int mTime;
        private SegmentTree seg;
        private Map<Integer, int[]> employeeMap = new HashMap<>();

        public EmployeeRadioLazy(int mTime) {
            init(mTime);
        }

        public void init(int mTime) {
            this.mTime = mTime;
            this.seg = new SegmentTree(MAX_TIME + 1);
            employeeMap.clear();
        }

        @Override
        public void add(int mId, int mStart, int mEnd) {
            int l = mStart;
            int r = mEnd - mTime;
            if (r < l)
                return;
            employeeMap.put(mId, new int[] { l, r });
            seg.add(l, r, 1);
        }

        @Override
        public void remove(int mId) {
            int[] interval = employeeMap.remove(mId);
            if (interval == null)
                return;
            seg.add(interval[0], interval[1], -1);
        }

        @Override
        public int count(int mBSTime) {
            return seg.get(mBSTime);
        }

        private static class SegmentTree {
            int[] tree, lazy;
            int size;

            SegmentTree(int n) {
                size = 1;
                while (size < n)
                    size <<= 1;
                tree = new int[size << 1];
                lazy = new int[size << 1];
            }

            private void push(int node, int l, int r) {
                if (lazy[node] != 0) {
                    tree[node] += lazy[node];
                    if (l != r) {
                        lazy[node << 1] += lazy[node];
                        lazy[node << 1 | 1] += lazy[node];
                    }
                    lazy[node] = 0;
                }
            }

            private void update(int node, int l, int r, int ql, int qr, int val) {
                push(node, l, r);
                if (qr < l || r < ql)
                    return;
                if (ql <= l && r <= qr) {
                    lazy[node] += val;
                    push(node, l, r);
                    return;
                }
                int mid = (l + r) >> 1;
                update(node << 1, l, mid, ql, qr, val);
                update(node << 1 | 1, mid + 1, r, ql, qr, val);
                tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]);
            }

            private int query(int node, int l, int r, int idx) {
                push(node, l, r);
                if (l == r)
                    return tree[node];
                int mid = (l + r) >> 1;
                if (idx <= mid)
                    return query(node << 1, l, mid, idx);
                else
                    return query(node << 1 | 1, mid + 1, r, idx);
            }

            void add(int l, int r, int val) {
                if (l > r)
                    return;
                update(1, 0, size - 1, l, r, val);
            }

            int get(int idx) {
                return query(1, 0, size - 1, idx);
            }
        }
    }

    // 5. 차등 배열
    public static class EmployeeRadioDiffArray implements EmployeeRadio {
        private static final int MAX_TIME = 2_000_001;
        private int mTime;
        private int[] diff = new int[MAX_TIME + 2]; // difference array
        private int[] psum = new int[MAX_TIME + 2]; // prefix sum cache
        private boolean psumDirty = true;
        private Map<Integer, int[]> emp = new HashMap<>();

        public EmployeeRadioDiffArray(int mTime) {
            this.mTime = mTime;
        }

        @Override
        public void add(int id, int start, int end) {
            int l = start;
            int r = end - mTime;
            if (r < l)
                return;
            diff[l] += 1;
            diff[r + 1] -= 1;
            emp.put(id, new int[] { l, r });
            psumDirty = true;
        }

        @Override
        public void remove(int id) {
            int[] interval = emp.remove(id);
            if (interval == null)
                return;
            int l = interval[0], r = interval[1];
            diff[l] -= 1;
            diff[r + 1] += 1;
            psumDirty = true;
        }

        @Override
        public int count(int t) {
            if (psumDirty) {
                psum[0] = diff[0];
                for (int i = 1; i < psum.length; i++)
                    psum[i] = psum[i - 1] + diff[i];
                psumDirty = false;
            }
            return psum[t];
        }
    }

    static class Operation {
        enum Type {
            ADD, REMOVE, COUNT
        }

        final Type type;
        final int id, start, end, t;

        private Operation(Type type, int id, int start, int end, int t) {
            this.type = type;
            this.id = id;
            this.start = start;
            this.end = end;
            this.t = t;
        }

        static Operation add(int id, int start, int end) {
            return new Operation(Type.ADD, id, start, end, 0);
        }

        static Operation remove(int id) {
            return new Operation(Type.REMOVE, id, 0, 0, 0);
        }

        static Operation count(int t) {
            return new Operation(Type.COUNT, 0, 0, 0, t);
        }
    }

    // 테스트 및 벤치마크
    public static void main(String[] args) {
        final int M_TIME = 500;
        final int N_EMP = 5000, N_REMOVE = 300, N_COUNT = 30_000;
        Random rnd = new Random(2025);

        int[] starts = new int[N_EMP], ends = new int[N_EMP];
        int maxStart = Integer.MIN_VALUE;
        for (int i = 0; i < N_EMP; i++) {
            int s = rnd.nextInt(1_990_000 - M_TIME);
            starts[i] = s;
            ends[i] = s + M_TIME + rnd.nextInt(1_000);
            if (s > maxStart)
                maxStart = s;
        }
        int tWorst = maxStart;

        // remove id 목록 준비
        Set<Integer> rem = new HashSet<>();
        while (rem.size() < N_REMOVE)
            rem.add(rnd.nextInt(N_EMP));
        int[] remArr = rem.stream().mapToInt(Integer::intValue).toArray();

        // 연산(Operation) 리스트 생성
        List<Operation> ops = new ArrayList<>();
        // add 연산
        for (int i = 0; i < N_EMP; i++)
            ops.add(Operation.add(i, starts[i], ends[i]));
        // remove 연산
        for (int id : remArr)
            ops.add(Operation.remove(id));
        // count 연산
        for (int i = 0; i < N_COUNT; i++)
            ops.add(Operation.count(tWorst));

        // 연산 순서 랜덤 섞기
        Collections.shuffle(ops, rnd);

        // 각 구현체별로 벤치마크
        for (String name : new String[] { "Simple", "Fenwick", "SegTree", "LazySeg", "DiffArray" }) {
            EmployeeRadio er;
            switch (name) {
                case "Simple":
                    er = new EmployeeRadioSimple(M_TIME);
                    break;
                case "Fenwick":
                    er = new EmployeeRadioFenwick(M_TIME, starts, ends);
                    break;
                case "SegTree":
                    er = new EmployeeRadioSegTree(M_TIME, starts, ends);
                    break;
                case "DiffArray":
                    er = new EmployeeRadioDiffArray(M_TIME);
                    break;
                default:
                    er = new EmployeeRadioLazy(M_TIME);
            }
            long t0 = System.nanoTime();
            long acc = 0;
            for (Operation op : ops) {
                switch (op.type) {
                    case ADD:
                        er.add(op.id, op.start, op.end);
                        break;
                    case REMOVE:
                        er.remove(op.id);
                        break;
                    case COUNT:
                        acc += er.count(op.t);
                        break;
                }
            }
            long t1 = System.nanoTime();
            double sec = (t1 - t0) / 1e9;
            System.out.printf("%s: 전체 랜덤 호출 합계 → %.3f초 (acc=%d)%n", name, sec, acc);
            System.out.println();
        }
        
    }
}

int answer = 0;
        for(int t = 0; t < maxLen; t++) {
            Map<String, Integer> map = new HashMap<>();
            for(List<Pos> s: shortRoutes) {
                if (t >= s.size()) {
                    continue;
                }
                Pos k = s.get(t);
                String value = k.y + "," + k.x;
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            for(int c: map.values()) {
                if (c > 1) {
                    answer++;
                }
            }
        }