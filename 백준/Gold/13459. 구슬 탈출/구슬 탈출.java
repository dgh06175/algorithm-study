import java.util.*;

class Point {
    int y, x;

    Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    Point(Point other) {
        this.y = other.y;
        this.x = other.x;
    }
}

public class Main {
    static final int[] dy = { 1, -1, 0, 0 };
    static final int[] dx = { 0, 0, 1, -1 };
    static int n, m;
    static short answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        char[][] ary = new char[n][m];
        for (int i = 0; i < n; i++) {
            ary[i] = sc.nextLine().toCharArray();
        }

        Point red = new Point(-1, -1);
        Point blue = new Point(-1, -1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ary[i][j] == 'R') {
                    red.y = i;
                    red.x = j;
                }
                if (ary[i][j] == 'B') {
                    blue.y = i;
                    blue.x = j;
                }
            }
        }
        attempt(ary, 1, red, blue);

        System.out.println(answer);
    }

    static void attempt(char[][] arr, int count, Point red, Point blue) {
        if (count > 10) {
            return;
        }

        for (int d = 0; d < 4; d++) {
            char[][] dupArr = new char[n][m];
            for (int i = 0; i < n; i++) {
                dupArr[i] = Arrays.copyOf(arr[i], m);
            }
            Point dupRed = new Point(red);
            Point dupBlue = new Point(blue);

            int result = tilt(dupArr, d, dupRed, dupBlue);
            if (result == 1) {
                answer = 1;
            } else if (result == 2) {
                continue;
            } else {
                attempt(dupArr, count + 1, dupRed, dupBlue);
            }
        }
    }

    /**
     * 
     * @param arr  기울일 배열
     * @param d    arr를 d 방향으로 기운다.
     * @param red  빨간공 좌표, 기운 방향으로 바뀜
     * @param blue 파란공 좌표, 기운 방향으로 바뀜
     * @return 빨간색 공만 구멍에 들어갔으면 1, 파란색 공 구멍에 들어갔으면 2, 둘다 아니면 0 반환
     */
    static int tilt(char[][] arr, int d, Point red, Point blue) {
        boolean redGoal = false;
        boolean blueGoal = false;

        // 빨간공 기울기
        while (true) {
            int ny = red.y + dy[d];
            int nx = red.x + dx[d];
            if (arr[ny][nx] == '.') {
                swap(arr, red.y, red.x, ny, nx);
                red.y = ny;
                red.x = nx;
            } else if (arr[ny][nx] == 'O') {
                arr[red.y][red.x] = '.';
                redGoal = true;
                break;
            } else {
                break;
            }
        }
        // 파란공 기울기
        while (true) {
            int ny = blue.y + dy[d];
            int nx = blue.x + dx[d];
            if (arr[ny][nx] == '.') {
                swap(arr, blue.y, blue.x, ny, nx);
                blue.y = ny;
                blue.x = nx;
            } else if (arr[ny][nx] == 'O') {
                arr[blue.y][blue.x] = '.';
                blueGoal = true;
                break;
            } else {
                break;
            }
        }
        // 빨간공 기울기
        while (true) {
            int ny = red.y + dy[d];
            int nx = red.x + dx[d];
            if (arr[ny][nx] == '.') {
                swap(arr, red.y, red.x, ny, nx);
                red.y = ny;
                red.x = nx;
            } else if (arr[ny][nx] == 'O') {
                arr[red.y][red.x] = '.';
                redGoal = true;
                break;
            } else {
                break;
            }
        }

        if (blueGoal) {
            return 2;
        }
        if (redGoal) {
            return 1;
        }
        return 0;
    }

    private static void swap(char[][] arr, int y1, int x1, int y2, int x2) {
        char tmp = arr[y1][x1];
        arr[y1][x1] = arr[y2][x2];
        arr[y2][x2] = tmp;
    }
}
