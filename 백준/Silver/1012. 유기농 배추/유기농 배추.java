import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean board[][] = new boolean[n][m];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[y][x] = true;
            }

            int answer = bfs(board, n, m);
            System.out.println(answer);
        }
    }

    static int bfs(boolean[][] board, int n, int m) {
        int answer = 0;

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!board[i][j] || visited[i][j]) {
                    continue;
                }
                answer++;

                Queue<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[] { i, j });
                visited[i][j] = true;

                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    int y = now[0];
                    int x = now[1];

                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (ny < 0 || nx < 0 || ny >= n || nx >= m) {
                            continue;
                        }
                        if (board[ny][nx] && !visited[ny][nx]) {
                            queue.offer(new int[] { ny, nx });
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }
        return answer;
    }
}