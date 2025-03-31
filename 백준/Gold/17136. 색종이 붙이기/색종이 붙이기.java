import java.io.*;
import java.util.*;

public class Main {
    static final int N = 10;
    static final int MAX_PAPER_COUNT = 5;
    static boolean[][] ary = new boolean[N][N];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ary[i][j] = st.nextToken().equals("0") ? false : true;
            }
        }

        int[] count = new int[6];
        myFunc(ary, count);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(ans);

    }

    private static void myFunc(boolean[][] ary, int[] count) {
        boolean isAllFalse = true;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!ary[y][x]) {
                    continue;
                }
                isAllFalse = false;
                for (int size = 5; size >= 1; size--) {
                    if (count[size] < MAX_PAPER_COUNT && isValid(ary, y, x, size)) { // 5x5 되면 붙히기
                        stickPaper(ary, y, x, size, count);
                        myFunc(ary, count);
                        unStickPaper(ary, y, x, size, count);
                    }
                }
                return;
            }
        }

        if (isAllFalse) {
            int paperCount = 0;
            for (int i = 1; i <= MAX_PAPER_COUNT; i++) {
                paperCount += count[i];
            }
            ans = Math.min(ans, paperCount);
        }
    }

    private static boolean isValid(boolean[][] ary, int y, int x, int size) {
        if (y + size > N || x + size > N) {
            return false;
        }

        boolean isValid = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!ary[y + i][x + j]) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private static void stickPaper(boolean[][] ary, int y, int x, int size, int[] count) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ary[y + i][x + j] = false;
            }
        }
        count[size] += 1;
    }

    private static void unStickPaper(boolean[][] ary, int y, int x, int size, int[] count) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ary[y + i][x + j] = true;
            }
        }
        count[size] -= 1;
    }
}
