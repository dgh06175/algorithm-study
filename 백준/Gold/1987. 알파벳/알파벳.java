import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Main {
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static int r, c;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        sc.nextLine();
        char[][] ary = new char[r][c];
        for (int i = 0; i < r; i++) {
            ary[i] = sc.nextLine().toCharArray();
        }
        dfs(ary, new BitSet(), 0, 0, 1);
        System.out.println(ans);
        sc.close();
    }

    static void dfs(char[][] ary, BitSet visited, int y, int x, int dist) {
        visited.set(ary[y][x]);
        ans = Math.max(ans, dist);

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= r || nx >= c) {
                continue;
            }
            if (visited.get(ary[ny][nx])) {
                continue;
            }
            visited.set(ary[ny][nx]);
            dfs(ary, visited, ny, nx, dist + 1);
            visited.clear(ary[ny][nx]);
        }
    }
}
