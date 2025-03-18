import java.util.*;

public class Main {
    static int n;
    static int m;
    static final int[] dy = { 1, -1, 0, 0 };
    static final int[] dx = { 0, 0, 1, -1 };
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        char ary[][] = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                ary[i] = str.toCharArray();
            }
        }

        search(ary, 1);
        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void search(char[][] ary, int count) {
        if (count > 10)
            return;
        for (int d = 0; d < 4; d++) {
            char[][] copyAry = new char[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copyAry[i][j] = ary[i][j];
                }
            }

            int result = tilt(copyAry, d);
            if (result == 1) {
                search(copyAry, count + 1);
            } else if (result == 2) {
                // System.out.println("도착! count: " + count);
                answer = Math.min(count, answer);
                return;
            }
        }
    }

    // 1: 아무 일 없음, 2: 빨간공 넣기 성공, 3: 파란공 들어감 실패, 4: 이전과 변화 없음
    private static int tilt(char[][] ary, int d) {
        int result = 1;
        boolean isSame = true;
        int[] redPos = new int[2];
        int[] bluePos = new int[2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (ary[i][j] == 'R') {
                    redPos = new int[] { i, j };
                }
                if (ary[i][j] == 'B') {
                    bluePos = new int[] { i, j };
                }
            }
        }

        while (true) {
            char nextChar = ary[redPos[0] + dy[d]][redPos[1] + dx[d]];
            if (nextChar == 'O') {
                isSame = false;
                result = 2;
                ary[redPos[0]][redPos[1]] = '.';
                break;
            } else if (nextChar == '.') {
                isSame = false;
                swap(ary, redPos[0], redPos[1], redPos[0] + dy[d], redPos[1] + dx[d]);
                redPos[0] += dy[d];
                redPos[1] += dx[d];
            } else {
                break;
            }
        }

        while (true) {
            char nextChar = ary[bluePos[0] + dy[d]][bluePos[1] + dx[d]];
            if (nextChar == 'O') {
                isSame = false;
                return 3;
            } else if (nextChar == '.') {
                isSame = false;
                swap(ary, bluePos[0], bluePos[1], bluePos[0] + dy[d], bluePos[1] + dx[d]);
                bluePos[0] += dy[d];
                bluePos[1] += dx[d];
            } else {
                break;
            }
        }
        if (result == 2)
            return result;

        while (true) {
            char nextChar = ary[redPos[0] + dy[d]][redPos[1] + dx[d]];
            if (nextChar == 'O') {
                isSame = false;
                return 2;
            } else if (nextChar == '.') {
                isSame = false;
                swap(ary, redPos[0], redPos[1], redPos[0] + dy[d], redPos[1] + dx[d]);
                redPos[0] += dy[d];
                redPos[1] += dx[d];
            } else {
                break;
            }
        }
        if (isSame)
            return 4;
        return result;
    }

    private static void swap(char ary[][], int y1, int x1, int y2, int x2) {
        char tmp = ary[y1][x1];
        ary[y1][x1] = ary[y2][x2];
        ary[y2][x2] = tmp;
    }
}
