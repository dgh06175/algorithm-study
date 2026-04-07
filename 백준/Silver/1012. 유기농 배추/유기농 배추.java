import java.util.*;
import java.io.*;

public class Main {
    static final int[] dy = {1, -1, 0, 0};
    static final int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean[][] board = new boolean[n][m];
            List<int[]> cabbages = new ArrayList<>(k);

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                board[y][x] = true;
                cabbages.add(new int[]{y, x});
            }

            sb.append(countComponents(board, cabbages, n, m)).append('\n');
        }

        System.out.print(sb);
    }

    static int countComponents(boolean[][] board, List<int[]> cabbages, int n, int m) {
        int answer = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        for (int[] pos : cabbages) {
            int sy = pos[0];
            int sx = pos[1];

            if (!board[sy][sx]) continue;

            answer++;
            board[sy][sx] = false;
            queue.offer(sy * m + sx);

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                int y = cur / m;
                int x = cur % m;

                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                    if (!board[ny][nx]) continue;

                    board[ny][nx] = false;
                    queue.offer(ny * m + nx);
                }
            }
        }

        return answer;
    }
}