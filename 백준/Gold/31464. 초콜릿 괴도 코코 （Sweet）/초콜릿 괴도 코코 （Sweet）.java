import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static final int[] dx = { 0, 0, 1, -1 };
    static final int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        char[][] cho = new char[n][n];
        List<int[]> ansList = new ArrayList<>();
        int choCount = 0;

        for (int i = 0; i < n; i++) {
            cho[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cho[i][j] == '#')
                    choCount += 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cho[i][j] == '.')
                    continue;

                // i,j 초콜릿 없애기
                cho[i][j] = '.';

                // System.out.println("(" + i + "," + j + ")");
                boolean isValid = checkIsAnswer(cho, choCount);

                if (isValid) {
                    ansList.add(new int[] { i + 1, j + 1 });
                }
                // i,j 초콜릿 복구
                cho[i][j] = '#';
            }
        }

        System.out.println(ansList.size());
        for (int[] ans : ansList) {
            System.out.println(ans[0] + " " + ans[1]);
        }
    }

    // 직전에 간 곳 말고, 다시 방문이 가능하면 안됨
    // 순회 시: visited 인데 직전위치 아닌곳으로 이동이 가능하면 return false
    private static boolean checkIsAnswer(char[][] cho, int choCount) {
        int[] start = null;
        out: for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cho[i][j] == '#') {
                    start = new int[] { i, j };
                    break out;
                }
            }
        }

        if (start == null)
            return false;

        int visitCount = 0;
        boolean[][] visited = new boolean[n][n];
        Queue<Node> q = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        visitCount = 1;
        q.offer(new Node(start, new int[] { -1, -1 }));

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int ny = node.pos[0] + dy[i];
                int nx = node.pos[1] + dx[i];

                if (ny < 0 || nx < 0 || ny >= n || nx >= n) {
                    continue;
                }

                if (cho[ny][nx] == '#') {
                    // 순회 시: visited 인데 직전위치 아닌곳으로 이동이 가능하면 return false
                    if (visited[ny][nx] && !(ny == node.bef_pos[0] && nx == node.bef_pos[1])) {
                        return false;
                    }
                    if (!visited[ny][nx]) {
                        q.offer(new Node(new int[] { ny, nx }, node.pos));
                        visitCount++;
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        return visitCount == choCount - 1;
    }
}

class Node {
    int[] pos;
    int[] bef_pos;

    Node(int[] pos, int[] bef_pos) {
        this.pos = pos;
        this.bef_pos = bef_pos;
    }
}