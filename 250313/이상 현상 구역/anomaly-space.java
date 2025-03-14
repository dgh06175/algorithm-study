import java.util.*;

class Anomaly {
    int y, x;
    int d, v;

    Anomaly(int y, int x, int d, int v) {
        this.y = y;
        this.x = x;
        this.d = d;
        this.v = v;
    }
}

class Transition {
    int face, y, x;

    Transition(int face, int y, int x) {
        this.face = face;
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int[][] space;
    static int[][][] wall; // 0 동, 1 서, 2 남, 3 북, 4윗면
    static Anomaly[] anomalies;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int n, m, f;

    static class Point_2D {
        int y, x, dist;

        Point_2D(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        space = new int[n][n];
        wall = new int[5][m][m];
        anomalies = new Anomaly[f];
        for (int i = 0; i < n; i++) {
            space[i] = new int[n];
            st = new StringTokenizer(sc.nextLine());
            for (int j = 0; j < n; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int l = 0; l < 5; l++) {
            wall[l] = new int[m][m];
            for (int i = 0; i < m; i++) {
                wall[l][i] = new int[m];
                st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < m; j++) {
                    wall[l][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        for (int i = 0; i < f; i++) {
            st = new StringTokenizer(sc.nextLine());
            anomalies[i] = new Anomaly(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        Point_2D start_2d = getStart2DPoint(space, wall);
        int[][][] anomalySpacesByTurn = getAnomalySpacesByTurn(space);
        int getAnswer = getShortestDistanceInSpace(space, anomalySpacesByTurn);
    }

    static Point_2D getStart2DPoint(int[][] space, int[][][] wall) {
        int[] spaceStartPoint = getSpaceStartPoint(space);
        int[] wallEndPoint = getWallEndPoint(space, spaceStartPoint, wall);
        int minDistToWallEndPoint = bfsMinDistToWallEndPoint(wall, wallEndPoint[0], wallEndPoint[1], wallEndPoint[2]);

        System.out.println(
                "바닥에서 시작점은 " + spaceStartPoint[0] + "," + spaceStartPoint[1] + "이고, 거리는: "
                        + (minDistToWallEndPoint + 1));
        return new Point_2D(spaceStartPoint[0], spaceStartPoint[1], minDistToWallEndPoint + 1);
    }

    private static int[] getSpaceStartPoint(int[][] space) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (space[i][j] == 3) {
                    for (int d = 0; d < 4; d++) {
                        int ni = i + dy[d];
                        int nj = j + dx[d];
                        if (space[ni][nj] == 0) {
                            return new int[] { ni, nj, d };
                        }
                    }
                }
            }
        }
        System.out.println("ERROR");
        return new int[2];
    }

    private static int[] getWallEndPoint(int[][] space, int[] spaceStartPoint, int[][][] wall) {
        int[] wallTopLeftPoint = getWallTopLeftPoint(space);
        int y = wallTopLeftPoint[0];
        int x = wallTopLeftPoint[1];
        int i = spaceStartPoint[0];
        int j = spaceStartPoint[1];
        int d = spaceStartPoint[2];

        if (d == 0) {
            return new int[] { 0, m - 1, m - i + y - 1 };
        } else if (d == 1) {
            return new int[] { 1, m - 1, i - y };
        } else if (d == 2) {
            return new int[] { 2, m - 1, i - x };
        } else if (d == 3) {
            return new int[] { 3, m - 1, m - i + x - 1 };
        }
        System.out.println("ERROR");
        return new int[3];
    }

    private static int[] getWallTopLeftPoint(int[][] space) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (space[i][j] == 3) {
                    return new int[] { i, j };
                }
            }
        }
        System.out.println("ERROR");
        return new int[2];
    }

    private static String faceName(int face) {
        switch (face) {
            case 0:
                return "동쪽면";
            case 1:
                return "서쪽면";
            case 2:
                return "남쪽면";
            case 3:
                return "북쪽면";
            case 4:
                return "윗면";
            default:
                return "알 수 없음";
        }
    }

    private static int bfsMinDistToWallEndPoint(int[][][] wall, int targetFace, int targetY, int targetX) {
        class Point_3D {
            int face, y, x, dist;

            Point_3D(int face, int y, int x, int dist) {
                this.face = face;
                this.y = y;
                this.x = x;
                this.dist = dist;
            }
        }

        int startFace = 4, startY = -1, startX = -1;
        outer: for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (wall[4][i][j] == 2) {
                    startY = i;
                    startX = j;
                    break outer;
                }
            }
        }
        if (startY == -1 || startX == -1) {
            System.out.println("ERROR: 시작점을 찾지 못했습니다.");
            return -1;
        }

        Queue<Point_3D> queue = new LinkedList<>();
        boolean[][][] visited3 = new boolean[5][m][m];

        System.out.println("BFS 시작: " + faceName(startFace) + " (" + startY + ", " + startX + ")");

        queue.offer(new Point_3D(startFace, startY, startX, 0));
        visited3[startFace][startY][startX] = true;

        while (!queue.isEmpty()) {
            Point_3D cur = queue.poll();
            System.out.println("Dequeue: " + faceName(cur.face) + " (" + cur.y + ", " + cur.x + ")  거리=" + cur.dist);

            if (cur.face == targetFace && cur.y == targetY && cur.x == targetX) {
                System.out.println("목표 도달: " + faceName(cur.face) + " (" + cur.y + ", " + cur.x + ")  거리=" + cur.dist);
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nface = cur.face;
                int ny = cur.y + dy[d];
                int nx = cur.x + dx[d];

                if (ny >= 0 && ny < m && nx >= 0 && nx < m) {
                    if (wall[nface][ny][nx] != 1 && !visited3[nface][ny][nx]) {
                        System.out.println("  일반 이동 Enqueue: " + faceName(nface) + " (" + ny + ", " + nx + ")  거리="
                                + (cur.dist + 1));
                        visited3[nface][ny][nx] = true;
                        queue.offer(new Point_3D(nface, ny, nx, cur.dist + 1));
                    }
                } else {
                    Transition trans = getTransition(cur.face, cur.y, cur.x, d);
                    if (trans != null) {
                        System.out.println("  전환: " + faceName(cur.face) + " (" + cur.y + ", " + cur.x + ") 에서 방향 " + d
                                + " 전환 -> " + faceName(trans.face) + " (" + trans.y + ", " + trans.x + ")");
                        nface = trans.face;
                        ny = trans.y;
                        nx = trans.x;
                        if (isValid(nface, ny, nx) && wall[nface][ny][nx] != 1 && !visited3[nface][ny][nx]) {
                            System.out.println("  전환 후 Enqueue: " + faceName(nface) + " (" + ny + ", " + nx + ")  거리="
                                    + (cur.dist + 1));
                            visited3[nface][ny][nx] = true;
                            queue.offer(new Point_3D(nface, ny, nx, cur.dist + 1));
                        }
                    }
                }
            }
        }
        System.out.println("BFS 종료: 목표에 도달할 수 없음.");
        return -1;
    }

    private static Transition getTransition(int face, int y, int x, int d) {
        // 윗면(4)에서의 전환
        if (face == 4) {
            if (d == 0 && x == m - 1) { // 오른쪽으로 벗어나면
                return new Transition(0, y, 0);
            } else if (d == 1 && x == 0) { // 왼쪽으로 벗어나면
                return new Transition(1, y, m - 1);
            } else if (d == 2 && y == m - 1) { // 아래로 벗어나면
                return new Transition(2, 0, x);
            } else if (d == 3 && y == 0) { // 위로 벗어나면
                return new Transition(3, m - 1, x);
            } else {
                return null;
            }
        }
        // 옆면에서 윗면으로의 전환 (옆면에서는 단 한쪽 가장자리만 윗면과 붙어 있다고 가정)
        else if (face == 0) { // 동쪽
            if (d == 1 && x == 0) { // 왼쪽 벗어나면 → 윗면
                return new Transition(4, y, m - 1);
            }
        } else if (face == 1) { // 서쪽
            if (d == 0 && x == m - 1) { // 오른쪽 벗어나면 → 윗면
                return new Transition(4, y, 0);
            }
        } else if (face == 3) { // 북쪽
            if (d == 2 && y == m - 1) { // 아래로 벗어나면 → 윗면
                return new Transition(4, 0, x);
            }
        } else if (face == 2) { // 남쪽
            if (d == 3 && y == 0) { // 위로 벗어나면 → 윗면
                return new Transition(4, m - 1, x);
            }
        }
        return null;
    }

    private static boolean isValid(int face, int y, int x) {
        return face >= 0 && face < 5 && y >= 0 && y < m && x >= 0 && x < m;
    }

    static int[][][] getAnomalySpacesByTurn(int[][] space) {
        return new int[1000000][][];
    }

    static int getShortestDistanceInSpace(int[][] space, int[][][] anomalySpacesByTurn) {
        return 0;
    }

    private void printAll() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(space[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

        for (int l = 0; l < 5; l++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(wall[l][i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();
        }

        for (int i = 0; i < f; i++) {
            System.out.println(anomalies[i].y + " " + anomalies[i].x + " " + anomalies[i].d + " " + anomalies[i].v);
        }
    }
}
