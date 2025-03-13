import java.util.*;

public class Main {
    static int[][] map;
    static Queue<Integer> wall;
    static int[] dy = { 0, 0, 1, -1 };
    static int[] dx = { 1, -1, 0, 0 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int m = sc.nextInt();
        map = new int[5][];
        wall = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            map[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };
        }
        for (int i = 0; i < m; i++) {
            wall.offer(sc.nextInt());
        }

        while (k-- > 0) {
            State best = calcBestState();
            map = best.copyMap;

            for (int[] ary : best.axis) {
                map[ary[0]][ary[1]] = 0;
            }

            int relicPoint = best.value + calcRelicPoint(map, wall);
            if (relicPoint <= 0)
                return;
            System.out.print(relicPoint + " ");
        }
    }

    private static State calcBestState() {
        State best = new State(0, 0, 0, Integer.MIN_VALUE, new ArrayList<>(), new int[5][]);
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int spin = 1; spin <= 3; spin++) {
                    State state = calcState(map, i, j, spin);
                    if (i == 1 && j == 1 && spin == 1)
                        best = state;
                    best = calcBest(best, state);
                }
            }
        }
        return best;
    }

    // 유물 획득 1차: 맵에서 현재 좌표와 회전 반경에 따라 유효한 State 만들어서 반환
    private static State calcState(int[][] map, int y, int x, int spin) {
        int[][] copyMap = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        if (spin == 1) {
            spin90(copyMap, y, x);
        } else if (spin == 2) {
            spin90(copyMap, y, x);
            spin90(copyMap, y, x);
        } else if (spin == 3) {
            spin90(copyMap, y, x);
            spin90(copyMap, y, x);
            spin90(copyMap, y, x);
        }

        List<int[]> relicCoordinates = calcRelicCount(copyMap);

        State state = new State(y, x, spin, relicCoordinates.size(), relicCoordinates, copyMap);
        return state;
    }

    private static List<int[]> calcRelicCount(int[][] copyMap) {
        List<int[]> relicCoordinates = new ArrayList<>();
        boolean[][] visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            visited[i] = new boolean[5];
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    List<int[]> coordinates = bfs(copyMap, visited, i, j);
                    if (coordinates.size() >= 3) {
                        relicCoordinates.addAll(coordinates);
                    }
                }
            }
        }
        return relicCoordinates;
    }

    private static List<int[]> bfs(int[][] copyMap, boolean[][] visited, int i, int j) {
        List<int[]> coordinates = new ArrayList<>();
        int num = copyMap[i][j];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { i, j });
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            coordinates.add(next);
            int y = next[0];
            int x = next[1];

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5 && !visited[ny][nx] && copyMap[ny][nx] == num) {
                    visited[ny][nx] = true;
                    queue.offer(new int[] { ny, nx });
                }
            }
        }
        return coordinates;
    }

    private static void spin90(int[][] copyMap, int y, int x) {
        int tmp = copyMap[y - 1][x - 1];
        copyMap[y - 1][x - 1] = copyMap[y + 1][x - 1];
        copyMap[y + 1][x - 1] = copyMap[y + 1][x + 1];
        copyMap[y + 1][x + 1] = copyMap[y - 1][x + 1];
        copyMap[y - 1][x + 1] = tmp;
        tmp = copyMap[y - 1][x];
        copyMap[y - 1][x] = copyMap[y][x - 1];
        copyMap[y][x - 1] = copyMap[y + 1][x];
        copyMap[y + 1][x] = copyMap[y][x + 1];
        copyMap[y][x + 1] = tmp;
    }

    private static State calcBest(State best, State state) {
        if (state.value > best.value)
            return state;
        if (state.value < best.value)
            return best;
        if (state.spin < best.spin)
            return state;
        if (state.spin > best.spin)
            return best;
        if (state.col < best.col)
            return state;
        if (state.col > best.col)
            return best;
        if (state.row < best.row)
            return state;
        return best;
    }

    // 유물 획득 2차로 얻은 점수 반환 및 wall, map 갱신
    private static int calcRelicPoint(int[][] map, Queue<Integer> wall) {
        int count = 0;
        while (true) {
            for (int j = 0; j < 5; j++) {
                for (int i = 4; i >= 0; i--) {
                    if (map[i][j] == 0) {
                        map[i][j] = wall.poll();
                    }
                }
            }

            List<int[]> relicCoordinates = calcRelicCount(map);
            if (relicCoordinates.size() == 0)
                break;
            count += relicCoordinates.size();
            for (int[] k : relicCoordinates) {
                map[k[0]][k[1]] = 0;
            }
        }
        return count;
    }
}

class State {
    int row, col, spin, value;
    List<int[]> axis;
    int[][] copyMap;

    State(int row, int col, int spin, int value, List<int[]> axis, int[][] copyMap) {
        this.row = row;
        this.col = col;
        this.spin = spin;
        this.value = value;
        this.axis = axis;
        this.copyMap = copyMap;
    }
}
