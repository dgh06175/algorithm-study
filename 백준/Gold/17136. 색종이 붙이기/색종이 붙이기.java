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

    // 재귀를 위한 함수, 함수 하나당 하나의 스티커를 붙힌다.
    private static void myFunc(boolean[][] ary, int[] count) {
        boolean isAllFalse = true; // 모두 0인 상태인지 검사하기 위한 플래그
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!ary[y][x]) {
                    continue;
                }
                isAllFalse = false;
                for (int size = 1; size <= 5; size++) {
                    if (isValid(ary, y, x, size, count)) { // 5x5 되면 붙히기
                        stickPaper(ary, y, x, size, count);
                        myFunc(ary, count);
                        unStickPaper(ary, y, x, size, count);
                    }
                }
                return;
            }
        }

        if (isAllFalse) { // 붙힐 곳 없을 때
            int paperCount = 0;
            for (int i = 1; i <= MAX_PAPER_COUNT; i++) {
                paperCount += count[i];
            }
            ans = Math.min(ans, paperCount);
        }
    }

    // size 크기의 종이를 붙힐 수 있는지 검사하는 함수
    private static boolean isValid(boolean[][] ary, int y, int x, int size, int[] count) {
        if (count[size] >= MAX_PAPER_COUNT) { // 이미 5장 붙힌 크기이면 안됨
            return false;
        }
        if (y + size > N || x + size > N) { // 종이 벗어나면 안됨
            return false;
        }

        boolean isValid = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!ary[y + i][x + j]) { // 0 있으면 안됨
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    // 종이 붙히는 함수, 동시에 크기별로 카운팅도 함
    private static void stickPaper(boolean[][] ary, int y, int x, int size, int[] count) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ary[y + i][x + j] = false;
            }
        }
        count[size] += 1;
    }

    // 종이 떼는 함수, 동시에 크기별로 카운팅도 되돌리기
    private static void unStickPaper(boolean[][] ary, int y, int x, int size, int[] count) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ary[y + i][x + j] = true;
            }
        }
        count[size] -= 1;
    }
}
