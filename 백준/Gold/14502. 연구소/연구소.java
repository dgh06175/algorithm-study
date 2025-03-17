import java.util.*;

public class Main {
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] ary = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ary[i][j] = sc.nextInt();
            }
        }

        // 0 부터 (n * m) - 1 까지의 3개 조합을 구해야 함
        List<List<Point>> combinations = getCombinations(n, m);

        int max = 0;
        for (List<Point> points : combinations) {
            int[][] tmpAry = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    tmpAry[i][j] = ary[i][j];
                }
            }
            boolean isValidMap = true;
            for (Point p : points) {
                if (ary[p.y][p.x] == 1 || ary[p.y][p.x] == 2)
                    isValidMap = false;
                tmpAry[p.y][p.x] = 1;
            }
            if (!isValidMap)
                continue;

            int safeAreaCount = bfs(tmpAry, n, m);
            max = Math.max(safeAreaCount, max);
        }
        System.out.println(max);
    }

    private static int bfs(int[][] map, int n, int m) {
        boolean[][] visited = new boolean[n][m];

        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    q.offer(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    if (!visited[ny][nx] && map[ny][nx] == 0) {
                        q.offer(new Point(ny, nx));
                        visited[cur.y][cur.x] = true;
                        map[ny][nx] = 2;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static class Point {
        int y, x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static List<List<Point>> getCombinations(int n, int m) {
        int end = n * m;
        List<List<Point>> combinations = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                for (int l = j + 1; l < end; l++) {
                    Point p1 = convertToPoint(i, n, m);
                    Point p2 = convertToPoint(j, n, m);
                    Point p3 = convertToPoint(l, n, m);
                    combinations.add(new ArrayList<>(Arrays.asList(p1, p2, p3)));
                }
            }
        }
        return combinations;
    }

    private static Point convertToPoint(int value, int n, int m) {
        int y = value / m;
        int x = value % m;
        return new Point(y, x);
    }
}
