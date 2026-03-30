import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dy = { 0, -1, 0, 1 };
    static int[] dx = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[][] board = new boolean[101][101];

        int n = Integer.parseInt(br.readLine());
        for (int t = 0; t < n; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            dragon(board, x, y, d, g);
            // printBoard(board);
        }
        int answer = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (board[i][j] && board[i + 1][j] && board[i][j + 1] && board[i + 1][j + 1]) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    static void dragon(boolean[][] board, int x, int y, int d, int g) {
        List<Integer> directList = new ArrayList<>();
        directList.add(d);

        for (int i = 0; i < g; i++) {
            for (int j = directList.size() - 1; j >= 0; j--) {
                int nj = (directList.get(j) + 1) % 4;
                directList.add(nj);
            }
        }

        int ny = y;
        int nx = x;
        board[ny][nx] = true;
        for (int i : directList) {
            ny = ny + dy[i];
            nx = nx + dx[i];
            board[ny][nx] = true;
        }
    }

    static void printBoard(boolean[][] board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}
// n세대는 지난 세대가 이동한 것의 반대로 이동하는데, 90도 회전해서 간다