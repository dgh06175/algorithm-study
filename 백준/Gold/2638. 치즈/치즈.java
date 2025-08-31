import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        boolean[][] ary = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                ary[i][j] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        int time = 0;
        boolean[][] realAir;
        while (true) {
            if (countRemains(ary) == 0) {
                break;
            }

            realAir = bfs(ary);
            melt(ary, realAir);

            time++;
        }

        System.out.println(time);
    }

    static void melt(boolean[][] ary, boolean[][] realAir) {
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (!ary[i][j]) {
                    continue;
                }
                int airCount = 0;
                for (int k = 0; k < 4; k++) {
                    int ni = i + dy[k];
                    int nj = j + dx[k];
                    if (realAir[ni][nj]) {
                        airCount++;
                    }
                }
                if (airCount >= 2) {
                    ary[i][j] = false;
                }
            }
        }
    }

    static boolean[][] bfs(boolean[][] ary) {
        boolean[][] visited = new boolean[n][m];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { 0, 0 });
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                    continue;
                }
                if (!visited[ny][nx] && !ary[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.offer(new int[] { ny, nx });
                }
            }
        }

        return visited;
    }

    static int countRemains(boolean[][] ary) {
        int count = 0;
        for (int i = 1; i < ary.length - 1; i++) {
            for (int j = 1; j < ary[0].length - 1; j++) {
                if (ary[i][j])
                    count++;
            }
        }
        return count;
    }
}

// -> 전체탐색 -> 10000
// -> 공기랑 2개이상 닿는 녀석들 체크
// bfs -> 40000
// -> 도달 가능한 녀석들 체크

// 8 9
// 0 0 0 0 0 0 0 0 0
// 0 0 0 0 0 0 0 0 0
// 0 1 1 0 0 0 1 1 0
// 0 1 0 1 1 1 0 1 0
// 0 1 0 0 1 0 0 1 0
// 0 1 0 1 1 1 0 1 0
// 0 1 1 0 0 0 1 1 0
// 0 0 0 0 0 0 0 0 0
