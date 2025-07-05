import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] graph = new int[n][m];
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            char[] input = sc.nextLine().toCharArray();
            for (int j = 0; j < m; j++) {
                graph[i][j] = input[j] - '0';
            }
        }

        System.out.println(bfs(graph));
    }

    static int bfs(int[][] graph) {
        // visited[i][j][l] = i, j 칸에 l = 0 이면 그냥 방문, l = 1 이면 벽 부순 상태로 방문
        boolean[][][] visited = new boolean[n][m][2];
        ArrayDeque<Point> queue = new ArrayDeque<>();

        queue.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.y == n - 1 && now.x == m - 1) {
                return now.dist;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }

                if (graph[ny][nx] == 1 && now.isBreak == 0) { // 부수고 가기
                    if (!visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        queue.offer(new Point(ny, nx, now.dist + 1, 1));
                    }
                } else if (graph[ny][nx] == 0) {
                    if (!visited[ny][nx][now.isBreak]) {
                        visited[ny][nx][now.isBreak] = true;
                        queue.offer(new Point(ny, nx, now.dist + 1, now.isBreak));
                    }
                }
            }
        }
        return -1;
    }
}

class Point {
    int y, x;
    int dist;
    int isBreak;

    Point(int y, int x, int dist, int isBreak) {
        this.y = y;
        this.x = x;
        this.dist = dist;
        this.isBreak = isBreak;
    }
}
