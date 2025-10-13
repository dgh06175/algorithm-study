import java.util.*;
import java.io.IOException;

public class Main {
    static int n;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        long b = sc.nextLong();

        int[][] ary = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ary[i][j] = sc.nextInt();
            }
        }

        int[][] ans = powerMatric(ary, b);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 행렬의 제곱 계산 (분할 정복)
    // A^(m+n) = A^m * A^n
    static int[][] powerMatric(int[][] base, long exp) {
        if (exp == 1L) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    base[r][c] %= 1000;
                }
            }
            return base;
        }
        int[][] half = powerMatric(base, exp / 2L);
        int[][] result = multiMatrix(half, half);
        if (exp % 2L == 0L) {
            return result;
        }
        return multiMatrix(result, base);
    }

    // 행렬의 곱 계산
    static int[][] multiMatrix(int[][] a, int[][] b) {
        int row = a.length;
        int col = b[0].length;
        int[][] result = new int[row][col];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int tmp = 0;
                for (int i = 0; i < n; i++) {
                    tmp += a[r][i] * b[i][c];
                }
                result[r][c] = tmp % 1000;
            }
        }
        return result;
    }
}