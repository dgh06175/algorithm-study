import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        SnakeGame snakeGame = new SnakeGame(n);

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            snakeGame.addApple(a - 1, b - 1);
        }

        int pastTime = 0;
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i <= l; i++) {
            if (i == l) {
                int endTime = snakeGame.move(n, 'X');
                System.out.println(endTime + pastTime);
                return;
            }
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            // System.out.println("----" + t + "초 전진, 끝나면 " + c + "로 회전 ----");
            int time = snakeGame.move(t - pastTime, c);
            if (snakeGame.getIsFinish()) {
                System.out.println(pastTime + time);
                return;
            }
            pastTime = t;
        }
    }
}

class SnakeGame {
    final static int[] dy = { -1, 0, 1, 0 }; // 위, 오른쪽, 아래, 왼쪽
    final static int[] dx = { 0, 1, 0, -1 };

    // 빈칸: 0, 몸통: 1, 사과: 2
    int[][] ary;
    int n;
    int d;
    int y, x;
    Deque<int[]> snake = new ArrayDeque<>();
    boolean isFinish;

    SnakeGame(int n) {
        this.ary = new int[n][n];
        this.n = n;
        this.y = 0;
        this.x = 0;
        this.d = 1;
        isFinish = false;
        snake.offer(new int[] { 0, 0 });
        ary[0][0] = 1;
    }

    void addApple(int i, int j) {
        this.ary[i][j] = 2;
    }

    int move(int t, char spin) {
        int time = 0;
        for (int p = 0; p < t; p++) {
            // System.out.println((p + 1) + "초, 방향:" + d);
            // for (int i = 0; i < n; i++) {
            // for (int j = 0; j < n; j++) {
            // System.out.print(ary[i][j] + " ");
            // }
            // System.out.println();
            // }
            int ny = y + dy[d];
            int nx = x + dx[d];
            snake.offer(new int[] { ny, nx });

            if (ny < 0 || nx < 0 || ny > n - 1 || nx > n - 1 || ary[ny][nx] == 1) {
                isFinish = true;
                return time + 1;
            }

            if (ary[ny][nx] != 2) {
                int[] tail = snake.poll();
                ary[tail[0]][tail[1]] = 0;
            }
            ary[ny][nx] = 1;
            y = ny;
            x = nx;
            time += 1;
        }
        if (spin != 'X') {
            d = calcDirection(d, spin);
        }
        return time;
    }

    private int calcDirection(int d, char spin) {
        if (spin == 'L') {
            if ((d - 1) == -1)
                return 3;
            return d - 1;
        } else if (spin == 'D') {
            if ((d + 1 == 4))
                return 0;
            return d + 1;
        }
        return d % 4;
    }

    boolean getIsFinish() {
        return isFinish;
    }
}