import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] board = new int[n][n];
        int y = -1;
        int x = -1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    y = i;
                    x = j;
                }
            }
        }
        board[y][x] = 0;

        int time = 0;
        int eatCount = 0;
        Shark shark = new Shark(y, x);
        int[][] dist = new int[n][n];
        while (true) {
            // 1. 모든 먹을 수 있는 물고기의 거리를 구한다.
            calcAllDist(board, dist, shark);
            int minDist = Integer.MAX_VALUE;
            Pos minPos = new Pos(-1, -1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == shark.pos.y && j == shark.pos.x) {
                        continue;
                    }
                    if (board[i][j] != 0 && board[i][j] < shark.size && minDist > dist[i][j]) {
                        minDist = dist[i][j];
                        minPos.y = i;
                        minPos.x = j;
                    }
                }
            }
            // 2. 없으면 끝
            if (minPos.y == -1 && minPos.x == -1) {
                break;
            }
            // 3. 가장 가까운 거리의 물고기를 먹으러 간다.
            board[minPos.y][minPos.x] = 0;
            shark.pos.y = minPos.y;
            shark.pos.x = minPos.x;
            eatCount++;
            if (eatCount == shark.size) {
                shark.size++;
                eatCount = 0;
            }
            // 4. 움직인 거리만큼 time을 더한다.
            time += minDist;
        }
        System.out.println(time);
    }

    // shark 부터 모든 물고기 까지의 거리 계산
    static void calcAllDist(int[][] board, int[][] dist, Shark shark) {
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        Queue<Pos> queue = new ArrayDeque<>();
        queue.offer(shark.pos);
        dist[shark.pos.y][shark.pos.x] = 0;
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                    continue;
                }
                if (shark.size < board[ny][nx]) {
                    continue;
                }
                if (dist[ny][nx] > dist[cur.y][cur.x] + 1) {
                    queue.offer(new Pos(ny, nx));
                    dist[ny][nx] = dist[cur.y][cur.x] + 1;
                }
            }
        }
    }

    static class Pos {
        int y, x;

        Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Shark {
        int size;
        Pos pos;

        Shark(int y, int x) {
            this.pos = new Pos(y, x);
            this.size = 2;
        }
    }
}